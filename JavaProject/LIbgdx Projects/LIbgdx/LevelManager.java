//Manages the Levels and speed
public class LevelManager {

    private int level;

    // constructor
    public LevelManager() {
        level = 1;
    }

    // Overloaded constructor
    // public LevelManager(int startLevel) {
    // level = startLevel;
    // }
    // Getter
    public int getLevel() {
        return level;
    }

    // setter
    public void setLevel(int level) {
        this.level = level;
    }

    public void nextLevel() {
        level++;
    }

    public void resetLevel() {
        level = 1;
    }

    public void increaseSpeed(Ball ball) {

        if (ball.xDir > 0)
            ball.xDir++;
        else
            ball.xDir--;

        if (ball.yDir > 0)
            ball.yDir++;
        else
            ball.yDir--;
    }
}