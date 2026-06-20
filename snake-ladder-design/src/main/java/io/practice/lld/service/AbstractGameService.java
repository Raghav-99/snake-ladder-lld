package io.practice.lld.service;

public abstract class AbstractGameService {
    public final GameState state;
    protected AbstractGameService(GameState state) {
        this.state = state;
    }
    public abstract boolean start();
    public abstract void move();
    public abstract boolean end();
    protected int[] calcPosition() {
        int val = state.getDie().peek();
        int y = state.getPlayer().getPosition().y, x = state.getPlayer().getPosition().x;
        int len = (int)Math.sqrt(state.getBoard().maxLen), gridPos = len*x + (y+1), newGridPos = val+gridPos;
        int ri = (newGridPos-1)/len, ci = (newGridPos-1)%len;
        return new int[] {ri, ci};
    }
}
