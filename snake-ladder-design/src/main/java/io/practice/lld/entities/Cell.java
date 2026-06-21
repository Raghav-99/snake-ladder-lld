package io.practice.lld.entities;

import java.util.HashSet;
import java.util.Set;

public class Cell implements Comparable<Cell> {
    public final int x;
    public final int y;
    private Obstacle obstacle;
    private final Set<Player> players = new HashSet<>();
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
    public Obstacle getObstacle() {
        return this.obstacle;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }
    public void removePlayer(Player p) {
        players.remove(p);
    }
    public boolean hasPlayer(Player p) {
        return players.contains(p);
    }
    public int getPlayerCount() {
        return players.size();
    } 

    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }
    @Override
    public int compareTo(Cell o) {
        Cell cell1 = this, cell2 = o;
        if(cell1.x < cell2.x) {
            return -1;
        }
        else if(cell1.x > cell2.x) {
            return 1;
        }
        return 0;
    }
}
