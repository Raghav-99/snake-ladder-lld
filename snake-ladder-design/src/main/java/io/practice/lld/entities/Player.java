package io.practice.lld.entities;

public class Player {
    private Cell position;
    private final String name;
    private boolean winner = false;

    public Player(Cell position, String name) {
        modifyPosition(position, null);
        this.name = name;
    }

    public void modifyPosition(Cell position, Cell oldPosition) {
        if(oldPosition != null) oldPosition.removePlayer(this);
        this.position = position;
        this.position.addPlayer(this);
    }

    public Cell getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public void setWinner(boolean b) {
        winner = true;
    }

    public boolean getWinner() {
        return this.winner;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
