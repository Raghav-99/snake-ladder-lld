package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Player;

public abstract class AbstractGameService {
    protected final GameState state;
    protected final int totalWinnersAllowed;
    protected AbstractGameService(GameState state, int totalWinnersAllowed, int totalPlayers) {
        if(totalWinnersAllowed > 0)
            this.totalWinnersAllowed = Math.min(totalWinnersAllowed, totalPlayers);
        else
            throw new IllegalArgumentException("totalWinnersAllowed argument must be greater than 0"); 
        this.state = state;
    }
    public GameState getGameState() {
        return this.state;
    }
    
    public abstract boolean start();
    protected abstract Cell move(Player p);
    
    public final boolean end() {
        return state.getWinners().size() == totalWinnersAllowed;
    }
    public final void next(Player player) {
        Cell newPos = move(player);
        player.modifyPosition(newPos, player.getPosition());
        state.setPlayer(player);
    }
    public final void markIfPlayerWon() {
        if(state.getBoard().lastCell.hasPlayer(state.getPlayer())) {
            state.addWinner();
        }
    }

    protected int[] calcPosition(Player p) {
        int val = state.getDie().peek();
        int y = p.getPosition().y, x = p.getPosition().x;
        int len = (int)Math.sqrt(state.getBoard().maxLen), gridPos = len*x + (y+1), newGridPos = val+gridPos;
        int ri = (newGridPos-1)/len, ci = (newGridPos-1)%len;
        return new int[] {ri, ci};
    }
}
