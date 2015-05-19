
package game_of_life;

public class Vector2D {
    private int x,y;
    
    public Vector2D(int x, int y) {
        setX(x);
        setY(y);
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }
}
