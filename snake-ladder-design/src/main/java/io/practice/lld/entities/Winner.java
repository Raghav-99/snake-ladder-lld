package io.practice.lld.entities;

public class Winner {
    private final Player player;
    private final int rollCount;
    public Winner(Player player, int rollCount) {
        this.player = player;
        this.rollCount = rollCount;
    }

    @Override
    public String toString() {
        return String.format("Winner: Player %s won the game by rolling a %d", player.getName(), rollCount);
    }

    @Override
    public int hashCode() {
        return player.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Winner other = (Winner) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.getName().equals(other.player.getName()))
            return false;
        return true;
    }
}
