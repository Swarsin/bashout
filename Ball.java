import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GameObject {
    private double dx, dy; // Velocity

    public Ball(double x, double y) {
        super(x, y, 10, 10, Color.WHITE);
        this.dx = 3; 
        this.dy = -3;
    }

    @Override
    public void update() {
        // TODO - Add dx and dy to x and y to make the ball move
        // TODO - Add basic wall bouncing logic
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO - Use gc.fillOval() to draw the ball
    }
}