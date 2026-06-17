package io.practice.lld.service;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;

public abstract class AbstractGameService {
    protected final GameState state;
    protected AbstractGameService(GameState state) {
        this.state = state;
    }
    abstract Cell start();
    abstract Cell move();
    abstract boolean end();
    // protected abstract Cell ob
}
