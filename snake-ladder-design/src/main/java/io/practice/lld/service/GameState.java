package io.practice.lld.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;

public class GameState {
    private Player p;
    private final Board board;
    private final Die die;
    private final Set<Winner> winners = new HashSet<>();

    public GameState(Board board, Die die) {
        this.board = board;
        this.die = die;
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
