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
    
    public abstract boolean start(Player p);
    
    protected final Cell move(Player p) {
        int[] pos = calcPosition(p);
        Cell newPos = state.getBoard().cellAt(pos[0], pos[1]);
        return newPos != null ? newPos : p.getPosition();
    }
    public final boolean end() {
        return state.getWinners().size() == totalWinnersAllowed;
    }
    public final TurnData next(Player player) {
        Cell oldPos = player.getPosition(), newPos = move(player), mutatedPos = null;
        Obstacle obstacle = state.getBoard().getObstacleAt(newPos);
        if(obstacle != null) {
            mutatedPos = state.getBoard().getMutatedPositionByObstacle(newPos);
            player.modifyPosition(mutatedPos, player.getPosition());
        }
        else {
            player.modifyPosition(newPos, player.getPosition());
        }
        return new TurnData(oldPos, newPos, mutatedPos, obstacle);
    }
    public final boolean markIfPlayerWon(Player currPlayer) {
        if(state.getBoard().lastCell.hasPlayer(currPlayer)) {
            state.addWinner(currPlayer);
            return true;
        }
        return false;
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

    public Obstacle getObstacleAt(Cell cell) {
        return state.getBoard().getObstacleAt(cell);
    }
}
