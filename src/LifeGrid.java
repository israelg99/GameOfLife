
package game_of_life;

import java.util.ArrayList;

public class LifeGrid {
    
    // GRID
    private final int WIDTH, HEIGHT;
    private Cell[] gameGrid;
    
    public LifeGrid(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        initGrid();
    }
    public LifeGrid(LifeGrid clone) {
        gameGrid = clone.getClone();
        this.WIDTH = clone.WIDTH;
        this.HEIGHT = clone.HEIGHT;
    }
    
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT() {
        return HEIGHT;
    }
    public Cell[] getClone() {
        Cell[] clone = new Cell[WIDTH*HEIGHT];
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                clone[y * WIDTH + x] = new Cell(getCell(x,y));
            }
        }
        return clone;
    }
    
    public Cell getCell(int x, int y) {
        return gameGrid[y * WIDTH + x];
    }
    public void setCell(Cell value, int x, int y) {
        gameGrid[y * WIDTH + x] = value;
    }
    public void setAlive(boolean alive, int x, int y) {
        getCell(x,y).isAlive(alive);
    }
    public void setDead(int x, int y) {
        setAlive(Cell.DEAD, x, y);
    }
    public void setAlive(int x, int y) {
        setAlive(Cell.ALIVE, x, y);
    }
    public void toggleLife(int x, int y) {
        getCell(x,y).toggleAlive();
    }
    
    public boolean isAlive(int x, int y) {
        return getCell(x,y).isAlive();
    }

    public void initGrid() {
        gameGrid = new Cell[WIDTH*HEIGHT];
        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                gameGrid[y * WIDTH + x] = new Cell();
            }
        }
    }
    
    public void updateCell(int x, int y) {
        Cell cell = getCell(x,y);
        ArrayList<Vector2D> nbs = GameOfLife.countNeighbors(x, y);
        if(cell.isAlive()) { // UPDATE ALIVE
            if(nbs.size() < 2 || nbs.size() > 3) {
                cell.isAlive(Cell.DEAD);
            }
        } else { // UPDATE DEAD
            if(nbs.size() == 3) {
                cell.isAlive(Cell.ALIVE);
            }
        }
    }
    
}
