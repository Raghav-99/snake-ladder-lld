package io.practice.lld;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;
import io.practice.lld.factory.ObstacleFactory;

public class Game {
    private final Board board;
    private final Queue<Player> players = new LinkedList<>();
    private final Die die;
    private final String gameType;
    private final Map<Cell, Obstacle> obstacleMap = new HashMap<>();
    private final Map<Cell, List<Cell>> obstacleGraph = new HashMap<>();

    public Map<Cell, Obstacle> getObstacleMap() {
        return obstacleMap;
    }

    public Map<Cell, List<Cell>> getObstacleGraph() {
        return obstacleGraph;
    }

    public Board getBoard() {
        return board;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public Die getDie() {
        return die;
    }

    public String getGameType() {
        return gameType;
    }

    public Game(Properties config) throws Exception {
        board = setupBoard(config);
        setupObstacles(config);
        setupPlayers(config);
        die = setupDie(config);
        gameType = setupGameType(config);
    }


    private String setupGameType(Properties config) {
        String type = config.getProperty("gameType");
        return type == null || type.isBlank() ? "Simple" : type;
    }

    private void setupPlayers(Properties config) throws NumberFormatException {
        String count = config.getProperty("playerCount", "4");
        if (count.isBlank()) {
            throw new IllegalArgumentException("playerCount cannot be blank");
        }
        int c = Integer.parseInt(count);
        if (c < 4) {
            throw new IllegalArgumentException("playerCount must be at least 4");
        }
        Cell start = board.cellAt(0, 0);
        for(int i = 1; i <= c; i++) {
            players.offer(new Player(start, String.valueOf(i)));
        }
    }

    private Die setupDie(Properties config) {
        return new Die();
    }

    private void setupObstacles(Properties config) throws Exception  {
        final String OBSTACLE_LITERAL = "obstacle_";
        if(!(config.containsKey(OBSTACLE_LITERAL.concat("snake")) && config.containsKey(OBSTACLE_LITERAL.concat("ladder"))))
            throw new UnsupportedOperationException("The game is not supported without snakes and ladders atleast!");

        List<String> obstacles = config.stringPropertyNames().stream().filter(prop -> prop.startsWith(OBSTACLE_LITERAL)).map((obs) -> obs.trim()).toList();
        for (String key : obstacles) {
            String obstaclePath = config.getProperty(key), obstacleType = key.substring(OBSTACLE_LITERAL.length());
            Properties obstacleConfig = new Properties();
            try(InputStream file = new FileInputStream(obstaclePath)) {
                obstacleConfig.loadFromXML(file);
                // todo: use priority queue instead
                Set<Cell> obsCells = setSpawnPoints(obstacleConfig, obstacleType);
                while(!obsCells.isEmpty()) {
                    if(!validObstacleSetup(new HashSet<>(),new HashSet<>(), obsCells, )) {
                            throw new IllegalStateException("Game building stage error: The obstacle setup is invalid. Either multiple obstacles occupy the same cell or there exists a cycle b/w 3 or more obstacles");
                    }
                }                
            } finally {
                
            }
        }
        
    }

    private Set<Cell> setSpawnPoints(Properties obstacleConfig, String type) throws IllegalArgumentException {
        String val = obstacleConfig.getProperty("coordinates");
        List<int[]> coords = val.transform(s -> parseCoordinates(s));
        Set<Cell> nodes = new HashSet<>();
        for (int[] coord : coords) {
            int x1 = coord[0], y1 = coord[1], x2 = coord[2], y2 = coord[3];
            Cell start = board.cellAt(x1, y1), end = board.cellAt(x2, y2);
            // if(start.getObstacle() == null && end.getObstacle() == null) {
                if(nodes.add(start)) {
                    throw new IllegalArgumentException("Error: Cell: %s "+start.toString()+ " is attempting to add multiple obstacles");
                }
                ObstacleFactory.createObstacle(type, start, end, obstacleMap, obstacleGraph);
                // start.setObstacle(obstacle);
                // end.setObstacle(obstacle);
            // }
        }
        return nodes;
    }

    // todo: replace the first if block with assert
    private List<int[]> parseCoordinates(String s) throws NumberFormatException {
        List<int[]> l = new ArrayList<>();
        if (s == null || s.isBlank() || s.charAt(0) != '[' || s.charAt(s.length()-1) != ']') {
            throw new IllegalArgumentException("coordinates must be enclosed in [ ]");
        }
        String _subString = s.substring(1, s.length() - 1);
        int idxEnd = _subString.indexOf(")"), idxStart = _subString.indexOf("(");
        while (idxStart > -1 && idxEnd > -1) {
            String unitSub = _subString.substring(idxStart+1, idxEnd);
            String[] coords = unitSub.split(",");
            if(coords.length != 4) throw new IllegalStateException("Illegal state. Too few or too many coordinates!");
            l.add(Stream.of(coords).mapToInt(c -> Integer.parseInt(c.trim())).toArray());
            idxStart = _subString.indexOf("(", idxEnd);
            idxEnd = _subString.indexOf(")", idxStart);
        }
        if(l.isEmpty()) throw new IllegalStateException("Invalid: Coordinates cannot be empty!"); 
        return l;
    }

    private Board setupBoard(Properties config) throws NumberFormatException {
        String rows = config.getProperty("board_size_row", "10"), cols = config.getProperty("board_size_col", "10");
        if (rows.isBlank() || cols.isBlank()) {
            throw new IllegalArgumentException("board_size_row and board_size_col cannot be blank");
        }
        int r = Integer.parseInt(rows), c = Integer.parseInt(cols);
        if (r != c) {
            throw new IllegalArgumentException("board rows and columns must be equal");
        }
        return new Board(r,c);
    }

    boolean validObstacleSetup(Set<Cell> seen, Set<Cell> visited, Set<Cell> obsCells, Cell c) {
        if(seen.contains(c)) return false;
        if(visited.contains(c)) return true;
        List<Cell> cells = obstacleGraph.get(c);
        if(cells != null) {
            seen.add(c);
            for(Cell neighbor : cells) {
                if(!validObstacleSetup(seen, visited, obsCells, neighbor))
                    return false;
            }
        }
        seen.remove(c);
        visited.add(c);
        obsCells.remove(c);
        return true;
    }
}
