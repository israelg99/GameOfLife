package game_of_life;

import javax.swing.JFrame;

public class Window extends JFrame {
    
    public final int WIDTH, HEIGHT;
    public final String TITLE;
    
    public Window(String title, int width, int height) {
        super();
        this.TITLE = title;
        this.WIDTH = width;
        this.HEIGHT = height;
        initWindow();
    }
    
    public void initWindow() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
