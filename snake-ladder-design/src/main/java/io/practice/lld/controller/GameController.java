package io.practice.lld.controller;

import java.util.Queue;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;
import io.practice.lld.service.AbstractGameService;

public class GameController {
    private final AbstractGameService gameService;
    private final String BEFORE = "BEFORE", AFTER = "AFTER";
    public GameController(AbstractGameService gameService) {
        this.gameService = gameService;
    }

    public void run(Queue<Player> players, Die die) throws InterruptedException {
        while (!gameService.start()) {
            playTurn(players, die);
        }
        System.out.println("Game has started...");
        
        while (!gameService.end()) {
            playTurn(players, die);
        }

        for (Winner winner : gameService.getGameState().getWinners()) {
            System.out.println(winner);
        }
    }

    private void printCurrentPlayerInfo(String stage, Player p, int roll) {
        Obstacle obstacle = p.getPosition().getObstacle();
        Cell cell = p.getPosition();
        System.out.println(String.format("stage: %s | player: %s | die_roll: %d | cell: %s | Obstacle: %s", stage, p.getName(), roll, cell.toString(), obstacle == null ? "null" : obstacle.toString()));
    }

    private void playTurn(Queue<Player> players, Die die) {
        Player currPlayer = null;
        do {
            currPlayer = players.poll();
        } while (!players.isEmpty() && currPlayer.getWinner());
        printCurrentPlayerInfo(BEFORE, currPlayer, -1);
        die.roll();
        gameService.next(currPlayer);
        printCurrentPlayerInfo(AFTER, currPlayer, die.peek());
        players.add(currPlayer);
        gameService.markIfPlayerWon();
    }
}
