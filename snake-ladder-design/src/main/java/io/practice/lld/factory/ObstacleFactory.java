package io.practice.lld.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.practice.lld.entities.Cell;
import io.practice.lld.entities.Ladder;
import io.practice.lld.entities.Obstacle;
import io.practice.lld.entities.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(String type, Cell start, Cell end, Map<Cell, Obstacle> obsMap, Map<Cell, List<Cell>> obsGraph) throws IllegalArgumentException {
        Obstacle obs = null;
        if (type.equals("snake")) {
            obs = new Snake(start, end);
        }
        else if(type.equals("ladder")) {
            obs = new Ladder(start, end);
        }
        else {
            throw new  UnsupportedOperationException("Obstacle type "+ (type.isBlank() ? "(blank)" : type) +" is not supported!");
        }
        
        fillObstacleMap(start, end, obsMap, obsGraph, obs);
        
        return obs;
    }

    private static void fillObstacleMap(Cell start, Cell end, Map<Cell,Obstacle> obsMap, Map<Cell,List<Cell>> obsGraph, Obstacle obs) throws IllegalArgumentException {
        if(obsMap.containsKey(start)) throw new IllegalArgumentException("Error: Multiple obstacles cannot start from same cell. Conflicting cells: "+start+" and "+end);
        obsMap.put(start, obs);
        obsGraph.compute(start, (k,v) -> v == null ? new ArrayList<>(List.of()) : v).add(end);
    }
}
