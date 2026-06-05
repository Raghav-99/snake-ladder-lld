package io.practice.lld;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Coordinates;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Snake;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
public abstract class AbstractGame {
    protected final Board board;
    protected final Queue<Player> players;
    private final GameLoop gameLoop;
    public AbstractGame(int rows, int cols, int pCount, int maxRollsPerTurn) {
        board = bootstrapBoard(rows, cols);
        
        players = bootstrapPlayers(pCount);
        gameLoop = new GameLoop(rows, maxRollsPerTurn);
    }
    protected abstract boolean isOver();
    protected abstract List<Snake> setSnakeSpawn();
    protected abstract List<Ladder> 
    protected bootstrapBoard(int rows, int cols) {

    }
    
    private Queue<Player> bootstrapPlayers(int count) {
        Queue<Player> players = new LinkedList<>();
        int i = 0;
        while (i++ < count) {
            players.add(new Player(new Coordinates(0,0), String.valueOf(i)));
        }
        return players;
    }
    
    public void play() {
        while(!isOver()) {
            Player p = players.peek();
            if(p != null) {
                gameLoop.start(p);
            }
            players.poll();
            if(!p.getHasWon()) players.add(p);
        }
        
    }
}
