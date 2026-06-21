package io.practice.lld.factory;

import io.practice.lld.Game;
import io.practice.lld.service.AbstractGameService;
import io.practice.lld.service.GameState;
import io.practice.lld.service.SimpleGameService;

public class ServiceFactory {
    private final String simpleGameType = "Simple";
    public AbstractGameService createService(Game game)  {
        GameState state = new GameState(game.getBoard(), game.getDie(), game.getPlayers().peek());
        int totalPlayers = game.getPlayers().size();
        if(game.getGameType().equals(simpleGameType)) {
            return new SimpleGameService(state, totalPlayers);
        }
        throw new UnsupportedOperationException("gameType "+game.getGameType()+" is not supported!");
    }
}
