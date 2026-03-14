import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static java.lang.Math.random;

public class GameEngine {
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int score = 0; // The game tracks and displays the player's score

    public GameEngine() {
        paddle = new Paddle(350, 550);
        ball = new Ball(400, 300);
        bricks = new ArrayList<>();

        // The bricks are created,

        int brickWidth = 60;
        int brickHeight = 20;
        int spacing = 5;
        int numBricksPerRow = 10;
        int heightOffset = 50; // The space b/w the top and where bricks start
        double powerUpChance = 0.1;

        for (int row = 0; row < 5; row++) { //I chose to do 5 rows
            for (int col = 0; col < numBricksPerRow; col++) {
                double x = col * (spacing + brickWidth) + spacing; // Each brick has a spacing to 1 side, and the added spacing is the final brick's spacing to wall
                double y = row * (spacing + brickHeight) + heightOffset;

                if (powerUpChance >  Math.random()) {
                    bricks.add(new SpecialBrick(x,y));
                } else {
                    bricks.add( new Brick(x,y, Color.WHITE)); // TODO - Change this to randomly select a color from a list
                }
            }
        }
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
        // Didn't do anything yet since bricks don't move.

        bricks.removeIf(Brick::isDestroyed);

    }

    private void checkCollisions() {
        // TODO - Write collision logic between ball, paddle, and bricks
        // TODO - If a brick is hit, call brick.hit() and add brick.getPointValue() to score
    }

    private void draw(GraphicsContext gc) {
        // I'm not using clearRect because I want a black background for now (since my blocks are white), in the case a custom background is implemented, I will use clearRect
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 600); // I'm assuming the canvas is 800 x 600
        paddle.draw(gc);
        ball.draw(gc);
        for (Brick b : bricks) b.draw(gc);


        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 10, 25);
    }
}