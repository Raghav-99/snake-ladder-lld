package io.practice.lld.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;

public class GameState {
    private Player p;
    private final Board board;
    private final Die die;
    private final Set<Winner> winners = new HashSet<>();

    public GameState(Board board, Die die, Player p, Map<Cell,Obstacle> obsMap, Map<Cell,List<Cell>> obsGraph) {
        this.board = board;
        this.die = die;
        this.p = p;
        board.setObstacleGraph(obsGraph);
        board.setObstacleMap(obsMap);
    }

    public Die getDie() {
        return this.die;
    }
    
    public Board getBoard() {
        return this.board;
    }

    void setPlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return this.p;
    }

    boolean addWinner() {
        p.setWinner();
        return winners.add(new Winner(p, this.die.peek()));
    }

    public List<Winner> getWinners() {
        return this.winners.stream().toList();
    }
}
