package io.practice.lld.service;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state, int totalPlayers) {
        super(state, 1, totalPlayers);
    }
    

    @Override
    public boolean start() {
        return state.getPlayer().getPosition() != state.getBoard().firstCell;
    }
}
