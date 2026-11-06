package io.practice.lld.controller;

import java.util.Queue;

import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;
import io.practice.lld.service.AbstractGameService;
import io.practice.lld.service.TurnData;

public class GameController {
    private final AbstractGameService gameService;
    public GameController(AbstractGameService gameService) {
        this.gameService = gameService;
    }

    public void run(Queue<Player> players, Die die) throws InterruptedException {
        Player p = null;
        int roll;
        int i=0;
        do {
            p = players.poll();
            roll = rollDie(die);
            players.add(p);
        } while (!gameService.canStart(roll));
        playTurn(p, roll, i++);
        System.out.println("--Game started by player "+p.getName()+"--");
        
        while (!gameService.end()) {
            p = players.poll();
            roll = rollDie(die);
            playTurn(p, roll, i++);
            if(!gameService.markIfPlayerWon(p))
                players.add(p);
        }

        for (Winner winner : gameService.getGameState().getWinners()) {
            System.out.println(winner);
        }
    }

    private void printCurrentTurnInfo(Player p, int roll, TurnData turnData, int i) {
        System.out.println(String.format("turn_id: %d | player: %s | die_roll: %d | turn_data: %s", i, p.getName(), roll, turnData == null ? "null" : turnData));
    }

    private void playTurn(Player p, int rollVal, int i) {
        TurnData turnData = gameService.next(p);
        printCurrentTurnInfo(p, rollVal, turnData, i);
    }

    private int rollDie(Die die) {
        die.roll();
        return die.peek();
    }
}
