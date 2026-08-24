package io.practice.lld.service;

import io.practice.lld.entities.Player;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state, int totalPlayers) {
        super(state, 1, totalPlayers);
    }
    

    @Override
    public boolean start(Player p) {
        return p.getPosition() != state.getBoard().firstCell;
    }
}
