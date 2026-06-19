package io.practice.lld.service;

import io.practice.lld.entities.Board;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;

public class GameState {
    private Player p;
    private final Board board;
    private final Die die;
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

    public void setPlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return this.p;
    }
}
