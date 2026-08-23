package io.practice.lld.entities;

import java.util.List;
import java.util.Map;

public class Board {
    private final Cell[][] cells;
    public final Cell firstCell;
    public final Cell lastCell;
    public final int maxLen;
    private Map<Cell,Obstacle> obstacleMap;
    private Map<Cell, List<Cell>> obstacleGraph;
    
    public void setObstacleGraph(Map<Cell, List<Cell>> obstacleGraph) {
        this.obstacleGraph = obstacleGraph;
    }

    public void setObstacleMap(Map<Cell, Obstacle> obsMap) {
        this.obstacleMap = obsMap;
    }

    public Board(int rows, int columns) {
        if (rows <= 1 || columns <= 1 || rows != columns) {
            throw new IllegalArgumentException("rows and columns must be greater than 1 and equal");
        }
        cells = new Cell[rows][columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        firstCell = cells[0][0];
        lastCell = cells[rows-1][columns-1];
        maxLen = rows * columns;
    }
    
    public Cell cellAt(int i, int j) {
        if(i >= 0 && i < cells.length && j >= 0 && j < cells[i].length) {
            return cells[i][j];
        }
        return null;
    }

    public Obstacle getObstacleAt(Cell c) {
        return obstacleMap.getOrDefault(c, null);
    }

    public Cell getMutatedPositionByObstacle(Cell c) {
        return obstacleGraph.get(c).getFirst();
    } 
}
