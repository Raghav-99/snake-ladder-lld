package io.practice.lld.service;

public class SimpleGameService extends AbstractGameService {
    public SimpleGameService(GameState state, int totalPlayers) {
        super(state, 1, totalPlayers);
    }
    

    @Override
    public boolean canStart(int dieRoll) {
        return dieRoll == 6;
    }
}
