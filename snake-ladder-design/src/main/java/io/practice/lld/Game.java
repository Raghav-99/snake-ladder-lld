package io.practice.lld;

import io.practice.lld.entities.Board;

public class Game extends AbstractGame {
    public Game(int rows, int cols, int pCount, int maxRollsPerTurn) {
        super(rows, cols, pCount, maxRollsPerTurn);
    }
    @Override
    protected boolean isOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOver'");
    }

    @Override
    protected Board bootstrapBoard(int rows, int cols) {
        // TODO Auto-generated method stub
        
    }

}
