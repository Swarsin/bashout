import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GameObject {
    protected double x, y, width, height;
    protected Color color;

    public GameObject(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Abstract methods, to force child classes to implement their own versions
    public abstract void update();
    public abstract void draw(GraphicsContext gc); // Vector graphics drawn here

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }
}