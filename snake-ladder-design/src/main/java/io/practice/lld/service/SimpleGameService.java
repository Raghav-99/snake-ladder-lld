package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state) {
        super(state);
    }
    

    @Override
    boolean end() {
        return state.movesLeft() > 0;
    }


    @Override
    Cell start() {
        int val = state.getDie().peek();
        int y = state.getPlayer().getPosition().y + val, x = state.getPlayer().getPosition().x;
        if(y >= state.getBoard().maxLen) {
            x++;
            y -= state.getBoard().maxLen;
        }
        return state.getBoard().cellAt(x, y);
    }


    @Override
    Cell move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

}
