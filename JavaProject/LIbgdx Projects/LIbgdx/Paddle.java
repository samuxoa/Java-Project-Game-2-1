import java.awt.*;

public class Paddle {

    // Encapsulation  
    
   private  int x;

    public Paddle(int x) {
        this.x = x;
    }

     // Getter
    public int getX() {
        return x;
    }
    // Setter
    public void setX(int x) {
        this.x = x;
    }

    public void moveLeft() {
        if (x > 10)
            x -= 5;
    }

    // Overloaded
    public void moveLeft(int speed) {
        if (x > 10)
            x -= speed;
    }

    public void moveRight() {
        if (x < 490)
            x += 5;
    }

    // Overloaded
    public void moveRight(int speed) {
        if (x < 490)
            x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, 440, 100, 10);
    }

    // Overloaded draw
    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x, 440, 100, 10);
    }

    public Rectangle getRect() {
        return new Rectangle(x, 440, 100, 10);
    }
}