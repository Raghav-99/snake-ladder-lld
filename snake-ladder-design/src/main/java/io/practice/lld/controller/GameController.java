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
        int i=0;
        do {
            p = players.peek();
            playTurn(players, die,i++);
        } while (!gameService.start(p));
        System.out.println("Game has started...");
        
        while (!gameService.end()) {
            playTurn(players, die, i++);
        }

        for (Winner winner : gameService.getGameState().getWinners()) {
            System.out.println(winner);
        }
    }

    private void printCurrentTurnInfo(Player p, int roll, TurnData turnData, int i) {
        System.out.println(String.format("turn_id: %d | player: %s | die_roll: %d | turn_data: %s", i, p.getName(), roll, turnData == null ? "null" : turnData));
    }

    private void playTurn(Queue<Player> players, Die die, int i) {
        if(players.isEmpty()) return;
        Player currPlayer = players.poll();
        die.roll();
        TurnData turnData = gameService.next(currPlayer);
        printCurrentTurnInfo(currPlayer, die.peek(), turnData, i);
        if(!gameService.markIfPlayerWon(currPlayer))
            players.add(currPlayer);
    }
}
