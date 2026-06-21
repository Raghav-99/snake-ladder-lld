package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state, int totalPlayers) {
        super(state, 1, totalPlayers);
    }
    

    @Override
    public boolean start() {
        return state.getPlayer().getPosition() != state.getBoard().firstCell;
    }


    @Override
    protected Cell move(Player p) {
        int[] pos = calcPosition(p);
        Cell newPos = state.getBoard().cellAt(pos[0], pos[1]);
        if(newPos != null) {
            Obstacle obstacle = newPos.getObstacle();
            if(obstacle != null) {
                newPos = obstacle.doAction(newPos);
            }
        }
        return newPos != null ? newPos : p.getPosition();
    }

}
