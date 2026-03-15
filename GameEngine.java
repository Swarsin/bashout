import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

public class GameEngine {
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private int score = 0; // The game tracks and displays the player's score

    public GameEngine() {
        paddle = new Paddle(350, 550);
        ball = new Ball(400, 300);
        //bricks = new ArrayList<>();
        CreateLevel();
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
    
    private void CreateLevel() {
        Random rand = new Random();
        
        int rows = 6;
        int columns = 10;
        
        double startX = 50;
        double startY = 60;
        
        double spacingX = 70;
        double spacingY = 30;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j<columns;j++) {
                double x = startX + i*spacingX;
                double y = startY + j*spacingY;
                
                double val = rand.nextDouble();
                
                if (val<0.1) {
                    bricks.add(new SpecialBrick(x,y,SpecialBrick.SpecialType.EXPLODING));
                }
                else if (val<0.2) {
                    bricks.add(new SpecialBrick(x,y,SpecialBrick.SpecialType.POWERUP));
                }
                else if (val<0.4) {
                    bricks.add(new Brick(x,y,Color.RED,3));
                }
                else {
                    bricks.add(new Brick(x,y,Color.BLUE,1));
                }
            }
        }
    }
}