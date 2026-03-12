import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpecialBrick extends Brick {
    private int hitsRemaining = 2; // Currently needs 2 hits to break, can change later accordingly

    public SpecialBrick(double x, double y) {
        super(x, y, Color.GOLD); 
        this.pointValue = 50; // Worth more points than normal bricks, change later accordingly
    }

    @Override
    public void hit() {
        // TODO - Override logic: decrease hitsRemaining. 
        // If hitsRemaining == 0, set isDestroyed = true.
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!isDestroyed) {
            // TODO - Draw a fancier brick to that it's a special brick
        }
    }
}