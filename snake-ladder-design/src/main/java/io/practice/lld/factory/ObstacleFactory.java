package io.practice.lld.factory;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Ladder;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(String type, Cell start, Cell end) {
        if (type.equals("snake")) {
            return new Snake(start, end);
        }
        if(type.equals("ladder")) {
            return new Ladder(start, end);
        }
        throw new  UnsupportedOperationException("Obstacle type "+ (type.isBlank() ? "(blank)" : type) +" is not supported!");
    }
}
