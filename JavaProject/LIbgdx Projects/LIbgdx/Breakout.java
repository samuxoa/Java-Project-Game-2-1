import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Breakout extends JPanel implements KeyListener, ActionListener {

  private  Ball ball;
  private   Paddle paddle;
  private BrickManager bricks;
  private  LevelManager levelManager;

  private  boolean moveLeft = false, moveRight = false;
  private  boolean play = true, paused = false;

   private int score = 0, highScore = 0;

   private Timer timer;
   private  String highScoreFile = "highscore.txt";

    public Breakout() {

        ball = new Ball(100, 250, -2, -3);
        paddle = new Paddle(240);
        bricks = new BrickManager();
        levelManager = new LevelManager();

        loadHighScore();

        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(8, this);
        timer.start();
    }
   @Override
    protected void paintComponent(Graphics g) {
          super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 500);

        if (play) {

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 16));

            //  Score & Level Display
            FontMetrics fm = g.getFontMetrics();

            String scoreText = "Score: " + score;
            String levelText = "Level: " + levelManager.getLevel();

            int scoreX = getWidth() - fm.stringWidth(scoreText) - 10;
            int levelX = getWidth() - fm.stringWidth(levelText) - 10;

            g.drawString(scoreText, scoreX, 20);
            g.drawString(levelText, levelX, 40);

            bricks.draw(g);
            paddle.draw(g);
            ball.draw(g);

        } 
        else {

            g.setColor(Color.white);

            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", 150, 200);

            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Score: " + score, 200, 260);

            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("High Score: " + highScore, 180, 300);

            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press R to Restart", 200, 350);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {

        if (play && !paused) {

            ball.move();

            if (moveLeft) 
                paddle.moveLeft();
            if (moveRight) 
                paddle.moveRight();

            if (ball.x < 0 || ball.x > 585) 
                ball.xDir = -ball.xDir;
            if (ball.y < 0) 
                ball.yDir = -ball.yDir;

            if (ball.getRect().intersects(paddle.getRect()))
                ball.yDir = -ball.yDir;

            int totalBricks = 0;

            for (int i = 0; i < bricks.getBricks().length; i++) {
                for (int j = 0; j < bricks.getBricks()[i].length; j++) {

                    if (bricks.getBricks()[i][j] == 1) {

                        totalBricks++;

                        if (ball.getRect().intersects(bricks.getBrickRect(i, j))) {

                            bricks.setBrickValue(0, i, j);
                            score += 10;

                            Toolkit.getDefaultToolkit().beep();
                            ball.yDir = -ball.yDir;
                        }
                    }
                }
            }

            //  Level System
            if (totalBricks == 0) {

                levelManager.nextLevel();
                levelManager.increaseSpeed(ball);

                ball = new Ball(100, 250, ball.xDir, ball.yDir);
                paddle = new Paddle(240);
                bricks.initialize();
            }

            if (ball.y > 470) {
                play = false;

                if (score > highScore) {
                    highScore = score;
                    saveHighScore();
                }
            }
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) 
            moveLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            moveRight = true;
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            paused = !paused;

        if (e.getKeyCode() == KeyEvent.VK_R && !play)
            restartGame();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            moveLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
            moveRight = false;
    }

    public void keyTyped(KeyEvent e) {}

    public void restartGame() {

        play = true;
        paused = false;

        score = 0;
        levelManager.resetLevel();

        ball = new Ball(100, 250, -2, -3);
        paddle = new Paddle(240);
        bricks.initialize();
    }

    void loadHighScore() {
        try {
            File f = new File(highScoreFile);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                highScore = Integer.parseInt(br.readLine());
                br.close();
            }
        } 
        catch (Exception e) {}
    }

    void saveHighScore() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(highScoreFile));
            bw.write(String.valueOf(highScore));
            bw.close();
        } 
        catch (Exception e) {}
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Breakout Game");
        Breakout game = new Breakout();

        frame.add(game);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}