
package game_of_life;

public class Cell {
    
    // STATIC FINALS
    public static final boolean DEAD = false, ALIVE = true;
    public static final int ENERGY = 100; // Homeostasis.
    
    private boolean isAlive;
    private int energy;
    
    public Cell(boolean isAlive, int energy) {
        this.isAlive = isAlive;
        this.energy = energy;
    }
    public Cell(Cell cell) {
        this(cell.isAlive, cell.getEnergy());
    }
    public Cell() {
        this(DEAD, ENERGY);
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    public void isAlive(boolean alive) {
        isAlive = alive;
    }
    public void toggleAlive() {
        isAlive(!isAlive());
    }
    
    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public void addEnergy(int energy) {
        this.energy += energy;
    }
    public void minusEnergy(int energy) {
        this.energy -= energy;
    }
}
