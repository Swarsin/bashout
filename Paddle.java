import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {
    public Paddle(double x, double y) {
        super(x, y, 100, 15, Color.BLUE);
    }

    @Override
    public void update() {
        // TODO - Add logic to move left/right based on keyboard input
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO - Use gc.fillRect() to draw the paddle
    }
}