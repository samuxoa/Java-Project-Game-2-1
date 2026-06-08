import java.awt.*;

// Manages the bricks in the game

public class BrickManager {
// Encapsulation
   private int bricks[][];
   private int brickWidth = 65, brickHeight = 20;

   private Color[] mainRowColors = {Color.pink, Color.red, Color.green, Color.blue};

   public BrickManager() {
        bricks = new int[9][9];
        initialize();
    }

  //Getter
    public int[][] getBricks(){
        return bricks;}

    public int getBrickWidth() {
        return brickWidth;
    }
    

    public void initialize() {
        for (int i = 0; i < bricks.length; i++) {
            int cols = (i < 2) ? 7 : 9;
            for (int j = 0; j < cols; j++)
                bricks[i][j] = 1;
            for (int j = cols; j < 9; j++)
                bricks[i][j] = 0;
        }
    }
    public void setBrickValue(int value, int row, int col) {
        bricks[row][col] = value;
    }

    public void draw(Graphics g) {
        int startX = 10, startY = 10;

        for (int i = 0; i < bricks.length; i++) {
            int cols = (i < 2) ? 7 : 9;

            for (int j = 0; j < cols; j++) {
                if (bricks[i][j] == 1) {

                    if (i == 0)
                    g.setColor(Color.cyan);
                    else if  (i == 1) 
                    g.setColor(Color.orange);
                    else {
                        int idx = (i - 2) % mainRowColors.length;
                        g.setColor(mainRowColors[idx]);
                    }

                    g.fillRect(startX + j * brickWidth,
                               startY + i * brickHeight,
                               brickWidth, brickHeight);

                    g.setColor(Color.black);
                    g.drawRect(startX + j * brickWidth,
                               startY + i * brickHeight,
                               brickWidth, brickHeight);
                }
            }
        }
    }

    public Rectangle getBrickRect(int i, int j) {
        return new Rectangle(10 + j * brickWidth,
                             10 + i * brickHeight,
                             brickWidth, brickHeight);
    }
}