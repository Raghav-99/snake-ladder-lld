package io.practice.lld.controller;

import java.util.Queue;

import io.practice.lld.entities.Die;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;
import io.practice.lld.service.AbstractGameService;
import io.practice.lld.service.TurnData;

public class GameController {
    private final AbstractGameService gameService;
    private final String BEFORE = "BEFORE", AFTER = "AFTER";
    public GameController(AbstractGameService gameService) {
        this.gameService = gameService;
    }

    public void run(Queue<Player> players, Die die) throws InterruptedException {
        Player p = null;
        do {
            p = players.peek();
            playTurn(players, die);
        } while (!gameService.start(p));
        System.out.println("Game has started...");
        
        while (!gameService.end()) {
            playTurn(players, die);
        }

        for (Winner winner : gameService.getGameState().getWinners()) {
            System.out.println(winner);
        }
    }

    private void printCurrentPlayerInfo(String stage, Player p, int roll, TurnData turnData) {
        System.out.println(String.format("stage: %s | player: %s | die_roll: %d | turn_data: %s", stage, p.getName(), roll, turnData == null ? "null" : turnData));
    }

    private void playTurn(Queue<Player> players, Die die) {
        if(players.isEmpty()) return;
        Player currPlayer = players.poll();
        printCurrentPlayerInfo(BEFORE, currPlayer, -1, null);
        die.roll();
        TurnData turnData = gameService.next(currPlayer);
        printCurrentPlayerInfo(AFTER, currPlayer, die.peek(), turnData);
        if(!gameService.markIfPlayerWon(currPlayer))
            players.add(currPlayer);
    }
}
