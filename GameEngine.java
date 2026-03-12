import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

public class GameEngine {
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int score = 0; // The game tracks and displays the player's score

    public GameEngine() {
        paddle = new Paddle(350, 550);
        ball = new Ball(400, 300);
        bricks = new ArrayList<>();
        // TODO - Use a loop to fill the bricks list with Brick and SpecialBrick objects
    }

    public void start(GraphicsContext gc) {
        new AnimationTimer() {
            public void handle(long now) {
                update();
                checkCollisions();
                draw(gc);
            }
        }.start(); // AnimationTimer handles the loop
    }

    private void update() {
        paddle.update();
        ball.update();
        // TODO - Loop through bricks and update() them
        // TODO - Use bricks.removeIf() to clear destroyed bricks
    }

    private void checkCollisions() {
        // TODO - Write collision logic between ball, paddle, and bricks
        // TODO - If a brick is hit, call brick.hit() and add brick.getPointValue() to score
    }

    private void draw(GraphicsContext gc) {
        // Clear the frame first
        // TODO - Clear the screen using gc.clearRect()
        paddle.draw(gc);
        ball.draw(gc);
        for (Brick b : bricks) b.draw(gc);
        
        // TODO - Draw the score text using gc.fillText()
    }
}