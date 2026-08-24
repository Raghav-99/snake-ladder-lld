package io.practice.lld.entities;

import java.util.HashSet;
import java.util.Set;

public class Cell implements Comparable<Cell> {
    public final int x;
    public final int y;
    private final Set<Player> players = new HashSet<>();
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
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
        return Integer.compare(cell1.y, cell2.y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
