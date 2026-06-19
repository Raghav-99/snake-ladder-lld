package io.practice.lld.entities;

public class Cell implements Comparable<Cell> {
    public final int x;
    public final int y;
    private Obstacle obstacle;
    private int playerCount;
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
    void increasePlayerCount() {
        playerCount += 1;
    }
    void decreasePlayerCount() {
        playerCount -= 1;
    }
    public int getPlayerCount() {
        return playerCount;
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
