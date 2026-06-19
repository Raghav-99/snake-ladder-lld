package io.practice.lld.entities;

import java.util.Random;

public class Die {
    private int rollValue = 0;
    public void roll() {
        Random random = new Random();
        rollValue = random.nextInt(1, 6);
    }

    public int peek() {
        return rollValue;
    }
}
