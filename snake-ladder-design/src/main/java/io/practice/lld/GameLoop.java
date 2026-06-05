package io.practice.lld;

import java.util.Random;

import io.practice.lld.entities.Coordinates;
import io.practice.lld.entities.Player;

public class GameLoop {
    private final int maxMovePerRow;
    private final int maxRollsPerTurn;
    public GameLoop(int maxMove, int maxRollsPerTurn) {
        this.maxMovePerRow = maxMove;
        this.maxRollsPerTurn = maxRollsPerTurn;
    }
    public void start(Player p) {
        int rollCount = rollDie();
        int i = 0;
        while(!p.getHasWon() && rollCount == 6 && i++ < maxRollsPerTurn)
        {
            move(p, rollCount);
        }
        p.setHasWon(p.getPosition().x() == maxMovePerRow - 1 && p.getPosition().y() == maxMovePerRow - 1);
    }

    private Coordinates move(Player p, int rc) {
        Coordinates pos = p.getPosition();
        int newY = pos.y();
        if(pos.x() + rc >= maxMovePerRow && pos.y() < maxMovePerRow) {
            newY += 1;
            int newX = maxMovePerRow - (pos.x() + rc - maxMovePerRow);
            p.setPosition(new Coordinates(newX, newY));
        }
        else if(pos.x() + rc < maxMovePerRow) {
            int newX = pos.x() + rc - 1;
            p.setPosition(new Coordinates(newX, newY));
        }
        return p.getPosition();
    }

    private static int rollDie() {
        Random random = new Random();
        return random.nextInt(1, 6);
    }
}
