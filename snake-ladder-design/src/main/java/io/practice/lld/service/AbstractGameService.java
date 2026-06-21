package io.practice.lld.service;

import io.practice.lld.entities.Player;

public abstract class AbstractGameService {
    protected final GameState state;
    protected AbstractGameService(GameState state) {
        this.state = state;
    }
    public GameState getGameState() {
        return this.state;
    }
    public abstract boolean start();
    public abstract void move();
    public abstract boolean end();
    
    public void next(Player player) {
        state.setPlayer(player);
    }
    public void markIfPlayerWon() {
        if(state.getBoard().lastCell.hasPlayer(state.getPlayer())) {
            state.addWinner();
        }
    }
    protected int[] calcPosition() {
        int val = state.getDie().peek();
        int y = state.getPlayer().getPosition().y, x = state.getPlayer().getPosition().x;
        int len = (int)Math.sqrt(state.getBoard().maxLen), gridPos = len*x + (y+1), newGridPos = val+gridPos;
        int ri = (newGridPos-1)/len, ci = (newGridPos-1)%len;
        return new int[] {ri, ci};
    }
}
