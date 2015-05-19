
package game_of_life;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GensCounter {
    
    public static int X = 10, Y = 25;
    public static int fontSize = 16;
    public static Font font = new Font("Century Gothic", Font.PLAIN, fontSize);
    public static Color color = Color.BLACK;
    public static String prefix = "Number of Gens: ";
    
    public static void printCounter(Graphics g, int x, int y) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(prefix + GameOfLife.getGens(), x, y);
    }
    public static void printCounter(Graphics g) {
        printCounter(g,X,Y);
    }
}
