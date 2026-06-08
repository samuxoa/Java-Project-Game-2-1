import java.awt.*;

public class Ball {

    int x, y, xDir, yDir;

    public Ball(int x, int y, int xDir, int yDir) {
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    // method
    public void move() {
        x += xDir;
        y += yDir;
    }
   //  Overloaded method 
    public void move(int speedMultiplier) {
        x += xDir * speedMultiplier;
        y += yDir * speedMultiplier;
    }
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 15, 15);
    }
    
   // Overloaded draw 
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(x, y, 15, 15);
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, 15, 15);
    }
}