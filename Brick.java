import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GameObject {
    protected boolean isDestroyed = false;
    protected int pointValue = 10;

    public Brick(double x, double y, Color color) {
        super(x, y, 60, 20, color);
    }

    public void hit() {
        // TODO - Logic for when the ball hits this brick
        isDestroyed = true;
    }

    public boolean isDestroyed() { return isDestroyed; }
    public int getPointValue() { return pointValue; }

    @Override
    public void update() {
        // Bricks won't move, so this will probably stay empty
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!isDestroyed) {
            // TODO - Use gc.fillRect() to draw the brick
        }
    }
}