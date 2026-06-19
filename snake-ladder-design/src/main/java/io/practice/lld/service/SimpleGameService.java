package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Obstacle;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state) {
        super(state);
    }
    

    @Override
    public boolean end() {
        return state.getBoard().lastCell.getPlayerCount() == 1;
    }


    @Override
    public boolean start() {
        move();
        return state.getPlayer().getPosition() != state.getBoard().firstCell;
    }


    @Override
    public void move() {
        int[] pos = calcPosition();
        Cell newPos = state.getBoard().cellAt(pos[0], pos[1]);
        if(newPos != null) {
            Obstacle obstacle = newPos.getObstacle();
            if(obstacle != null) {
                newPos = obstacle.doAction(newPos);
            }
            state.getPlayer().modifyPosition(newPos, state.getPlayer().getPosition());
        }
    }

}
