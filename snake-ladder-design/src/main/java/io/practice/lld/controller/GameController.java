package io.practice.lld.controller;

import java.util.Queue;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Die;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Player;
import io.practice.lld.entities.Winner;
import io.practice.lld.service.AbstractGameService;
import io.practice.lld.service.GameState;

public class GameController {
    private final AbstractGameService gameService;
    public GameController(AbstractGameService gameService) {
        this.gameService = gameService;
    }

    public void run(Queue<Player> players, Die die) throws InterruptedException {
        final String BEFORE = "BEFORE", AFTER = "AFTER";
        boolean started = false;
        do {
            playTurn(players, die);
            printCurrentState(BEFORE);
            started = gameService.start();
            printCurrentState(AFTER);
        } while (!started);
        
        System.out.println("Game has started...");
        
        do {
            playTurn(players, die);
            printCurrentState(BEFORE);
            gameService.move();
            gameService.markIfPlayerWon();
            printCurrentState(AFTER);
        } while (!gameService.end());

        for (Winner winner : gameService.getGameState().getWinners()) {
            System.out.println(winner);
        }
    }

    private void printCurrentState(String stage) {
        GameState state = gameService.getGameState();
        Player p = state.getPlayer();
        int roll = state.getDie().peek();
        Obstacle obstacle = p.getPosition().getObstacle();
        Cell cell = p.getPosition();
        System.out.println(String.format("stage: %s | player: %s | die_roll: %d | cell: %s | Obstacle: %s", stage, p.getName(), roll, cell.toString(), obstacle == null ? "null" : obstacle.toString()));
    }

    private void playTurn(Queue<Player> players, Die die) {
        Player currPlayer = null;
        do {
            currPlayer = players.poll();
        } while (!players.isEmpty() && currPlayer.getWinner());
        gameService.next(currPlayer);
        players.add(currPlayer);
        die.roll();
    }
}
