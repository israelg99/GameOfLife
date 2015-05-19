
package game_of_life;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class GameGrid extends JComponent implements MouseListener, KeyListener {
   
    // STATIC FINALS
    public static final Color COLOR_DEAD = Color.WHITE, COLOR_ALIVE = Color.BLACK, COLOR_OUTLINE = Color.GRAY;
    public static final int PAUSE_KEY = 32, RESET_KEY = 27, RESET_GENS_KEY = 8, INIT_KEY = 80;
    
    // GRID VARIABLES
    private final int WIDTH, HEIGHT;
    private final int ROW, COL;
    private final int SIZE;
    private Dimension preferredSize;
    
    // UI VARIABLES
    private Cursor cursor;
    
    // TIMER VARIABLES
    private Timer timer;
    private TimerTask update;
    private int FPS;
    
    private GameGrid(int ROW, int COL, int SIZE, int FPS) {
        this.ROW = ROW;
        this.COL = COL;
        this.SIZE = SIZE;
        this.WIDTH = this.SIZE * ROW;
        this.HEIGHT = this.SIZE * COL;
        preferredSize = new Dimension(this.WIDTH, this.HEIGHT);
        setPreferredSize(preferredSize);
        setMinimumSize(preferredSize);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.FPS = FPS;
        
        setupMouseListener();
        setupKeyListener();
        scheduleRender();
    }
    
    public GameGrid(int SIZE) {
        this(GameOfLife.gameGrid.getWIDTH(), GameOfLife.gameGrid.getHEIGHT(), SIZE, GameOfLife.getDelay());
    }
    
    @Override
    public void paint(Graphics g) {
        renderGame(g);
        printGensCounter(g);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    private void printGensCounter(Graphics g) {
        GensCounter.printCounter(g);
    }
    private void renderGame(Graphics g) {
        for(int y = 0; y < COL; y++) {
            for(int x = 0; x < ROW; x++) {
                if(GameOfLife.gameGrid.isAlive(x, y)) {
                    drawAlive(g, x, y);
                } else {
                    drawDead(g, x, y);   
                }
            }
        }
    }
    
    private void setupMouseListener() {
        addMouseListener(this);
    }
    private void setupKeyListener() {
        addKeyListener(this);
    }
    public void requestGridFocus() {
        setFocusable(true);
        requestFocusInWindow();
    }
    
    private void scheduleRender() {
        // SETTING UP TIMER AND TASK
        timer = new Timer();
        update = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
        
        timer.scheduleAtFixedRate(update, FPS, FPS);
    }
    
    public int getCellSize() {
        return SIZE;
    }
    
    private void drawRect(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.drawRect(x*SIZE, y*SIZE, SIZE, SIZE); 
    }
    private void fillRect(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x*SIZE, y*SIZE, SIZE, SIZE); 
    }
    private void drawAlive(Graphics g, int x, int y) {
        fillRect(g, x, y, COLOR_ALIVE);
        drawRect(g, x, y, COLOR_OUTLINE);
    }
    private void drawDead(Graphics g, int x, int y) {
        fillRect(g, x, y, COLOR_DEAD);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GameOfLife.gameGrid.toggleLife(e.getX()/SIZE, e.getY()/SIZE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == PAUSE_KEY) {
            GameOfLife.toggleGame();
        } else if(e.getKeyCode() == RESET_KEY) {
            GameOfLife.safePause();
            GameOfLife.gameGrid.initGrid();
        } else if(e.getKeyCode() == RESET_GENS_KEY) {
            GameOfLife.resetGens();
        } else if(e.getKeyCode() == INIT_KEY) {
            GameOfLife.safePause();
            GameOfLife.initGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
