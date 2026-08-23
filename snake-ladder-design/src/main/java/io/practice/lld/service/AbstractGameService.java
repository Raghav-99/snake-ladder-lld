package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Obstacle;
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
    
    protected final Cell move(Player p) {
        int[] pos = calcPosition(p);
        Cell newPos = state.getBoard().cellAt(pos[0], pos[1]);
        Obstacle obstacle = state.getBoard().getObstacleAt(newPos);
        if(obstacle != null) {
            newPos = state.getBoard().getMutatedPositionByObstacle(newPos);
        }
        return newPos != null ? newPos : p.getPosition();
    }
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
        int maxLen = state.getBoard().maxLen;
        int len = (int)Math.sqrt(maxLen), gridPos = len*x + (y+1), newGridPos = val+gridPos;
        if(newGridPos <= maxLen)
        {
            int ri = (newGridPos-1)/len, ci = (newGridPos-1)%len;
            return new int[] {ri, ci};
        }
        return new int[] {x,y};
    }
}
