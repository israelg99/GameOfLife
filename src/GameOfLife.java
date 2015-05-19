
package game_of_life;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOfLife {
    
    // GAME VARIABLES
    public static LifeGrid gameGrid;
    
    // TIMER VARIABLES
    private static int DELAY;
    private static Timer timer;
    private static TimerTask update;
    private static boolean isGamePaused;
    private static int gens;
    
    public static void init(int ROW, int COL, int delay) {
        
        // GAME SETUP
        gameGrid = new LifeGrid(ROW, COL);
        DELAY = delay;
        isGamePaused = true;
        gens = 0;
        
        // Init GAME
        gameGrid.initGrid();
        
        // TIMER SETUP
        setupTimer();
    }
    
    public static void setupTimer() {
        // SETTING UP TIMER AND TASK
        timer = new Timer();
        update = new TimerTask() {
            @Override
            public void run() {
                if(!isGamePaused) {
                    gens++;
                    update();
                }
            }
        };
        
        timer.scheduleAtFixedRate(update, DELAY, DELAY);
    }
    public static void cancelTimer() {
        timer.cancel();
    }
    public static void safePause() {
        pauseGame();
        try {
            Thread.sleep(GameOfLife.getDelay()*2);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameGrid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void pauseGame() {
        isGamePaused = true;
    }
    public static void resumeGame() {
        isGamePaused = false;
    }
    public static void toggleGame() {
        isGamePaused = !isGamePaused;
    }
    public static boolean isGamePaused() {
        return isGamePaused;
    }
    public static int getGens() {
        return gens;
    }
    public static void resetGens() {
        gens = 0;
    }
    
    
    public static void initGame() {
        gameGrid.initGrid();
        for(int y = 0; y < gameGrid.getHEIGHT() - 1; y++) {
            for(int x = 0; x < gameGrid.getWIDTH() - 1; x++) {
                if(Math.random() < 0.1) {
                    gameGrid.setAlive(x, y);
                }
            }
        }
    }
    
    public static int getDelay() {
        return DELAY;
    }
    
    // GAME UPDATE // WORKS WITH TIMER
    // GAME OF LIFE = 2|3 Stay alive, 3 Become Alive, otherwise dead.
    public static void update() {
        LifeGrid tempGrid = new LifeGrid(gameGrid);
        for(int y = 0; y < tempGrid.getHEIGHT() - 1; y++) {
            for(int x = 0; x < tempGrid.getWIDTH() - 1; x++) {
                tempGrid.updateCell(x,y);
            }
        }
        gameGrid = new LifeGrid(tempGrid);
    }
    
    // NEIGHBORS COUNT FUNCTION
    public static ArrayList<Vector2D> countNeighbors(int x, int y) {
        ArrayList<Vector2D> nbs = new ArrayList<Vector2D>();
        if(x > 0) {
            if(y > 0 && gameGrid.isAlive(x-1, y-1)) {
                nbs.add(new Vector2D(x-1,y-1));
            }
            if(gameGrid.isAlive(x-1, y)) {
                nbs.add(new Vector2D(x-1,y));
            }
            if(y < gameGrid.getHEIGHT() && gameGrid.isAlive(x-1, y+1)) {
                nbs.add(new Vector2D(x-1,y+1));
            }
        }
        if(x < gameGrid.getWIDTH()) {
            if(y > 0 && gameGrid.isAlive(x+1, y-1)) {
                nbs.add(new Vector2D(x+1,y-1));
            }
            if(gameGrid.isAlive(x+1, y)) {
                nbs.add(new Vector2D(x+1,y));
            }
            if(y < gameGrid.getHEIGHT() && gameGrid.isAlive(x+1, y+1)) {
                nbs.add(new Vector2D(x+1,y+1));
            }
        }
        if(y > 0 && gameGrid.isAlive(x, y-1)) {
            nbs.add(new Vector2D(x,y-1));
        }
        if(y < gameGrid.getHEIGHT() && gameGrid.isAlive(x, y+1)) {
            nbs.add(new Vector2D(x,y+1));
        }
        
        return nbs;
    }
    
}
