package io.practice.lld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.stream.Stream;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Ladder;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Snake;
import io.practice.lld.service.GameState;

public class Game {
    private final Board board;
    private final Queue<Player> players = new LinkedList<>();
    private final Die die;
    public Board getBoard() {
        return board;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public Die getDie() {
        return die;
    }

    public Game(Properties config) throws Exception {
        board = setupBoard(config);
        setupObstacles(config, "spawn.snakes");
        setupObstacles(config, "spawn.ladders");
        setupPlayers(config);
        die = setupDie(config);
    }
    
    private void setupPlayers(Properties config) throws NumberFormatException {
        String count = config.getProperty("playerCount", "4");
        assert !count.isBlank();
        int c = Integer.parseInt(count);
        assert c >= 4;
        Cell start = board.cellAt(0, 0);
        for(int i = 1; i <= c; i++) {
            players.offer(new Player(start, String.valueOf(i)));
        }
    }

    private Die setupDie(Properties config) {
        return new Die();
    }

    private void setupObstacles(Properties config, String entity) throws Exception {
        String val = config.getProperty(entity, "");
        assert !val.isBlank();
        List<int[]> coords = val.transform(s -> parseCoordinates(s));
        for (int[] coord : coords) {
            int x1 = coord[0], y1 = coord[1], x2 = coord[2], y2 = coord[3];
            Cell start = board.cellAt(x1, y1), end = board.cellAt(x2, y2);
            if(start.getObstacle() == null && end.getObstacle() == null) {
                Obstacle obstacle = null;
                if(entity.equals("spawn.snakes") && start.compareTo(end) > 0) {
                    obstacle = new Snake(start, end);
                }
                else if(entity.equals("spawn.ladders") && start.compareTo(end) < 0) {
                    obstacle = new Ladder(start, end);
                }
                else {
                    throw new Exception("Error: Invalid snakes and ladders coordinates configuration!");
                }
                start.setObstacle(obstacle);
                end.setObstacle(obstacle);
            }
        }
    }

    // todo: replace the first if block with assert
    private List<int[]> parseCoordinates(String s) throws NumberFormatException {
        List<int[]> l = new ArrayList<>();
        assert s.charAt(0) == '[' && s.charAt(s.length()-1) == ']';
        String _subString = s.substring(1, s.length() - 1);
        int idxEnd = _subString.indexOf(")"), idxStart = _subString.indexOf("(");
        while (idxStart > -1 && idxEnd > -1) {
            String unitSub = _subString.substring(idxStart+1, idxEnd);
            String[] coords = unitSub.split(",");
            assert coords.length == 4;
            l.add(Stream.of(coords).mapToInt(c -> Integer.parseInt(c.trim())).toArray());
            idxStart = _subString.indexOf("(", idxEnd);
            idxEnd = _subString.indexOf(")", idxStart);
        }
        return l;
    }

    private Board setupBoard(Properties config) throws NumberFormatException {
        String rows = config.getProperty("board_size_row", "10"), cols = config.getProperty("board_size_col", "10");
        assert !rows.isBlank() && !cols.isBlank();
        int r = Integer.parseInt(rows), c = Integer.parseInt(cols);
        assert r == c;
        return new Board(r,c);
    }
}
