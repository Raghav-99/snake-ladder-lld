package io.practice.lld.entities;

import java.util.Random;

public class Die {
    private int rollValue = 0;
    public int roll() {
        Random random = new Random();
        int rv = rollValue = random.nextInt(1, 6);
        return rv;
    }

    public int peek() {
        return rollValue;
    }
}
