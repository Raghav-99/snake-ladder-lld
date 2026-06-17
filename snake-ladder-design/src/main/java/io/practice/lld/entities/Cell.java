package io.practice.lld.entities;

public class Cell {
    public final int x;
    public final int y;
    private boolean isUsed;
    private Obstacle obstacle;
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
    public Obstacle getObstacle() {
        return this.obstacle;
    }
    public boolean getIsUsed() {
        return this.isUsed;
    }
}
