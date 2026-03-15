import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GameObject {
    protected double x;
    protected double y;
    //brick dimensions
    protected final double width = 60;
    protected final double height = 20;
    
    protected int hitpoints;
    private int maxHitpoints;
    protected boolean destroyed = false;
    protected int pointValue = 100;
    
    protected Color color;

    public Brick(double x, double y, Color color, int hitpoints) {
        super(x, y, 60, 20, color);
        this.hitpoints = hitpoints;
        this.maxHitpoints = hitpoints;
    }
    
    @Override
    public void update() {
        //bricks don't need to be updated
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        if (destroyed == false) {
            gc.setFill(color);
            gc.fillRect(x,y,width,height);
            gc.setStroke(Color.BLACK); //BLACKED OUT(LINE) LIKE A PHANTOM
            gc.strokeRect(x,y,width,height);
        }
    }
    
    public void hit() {
        
        hitpoints--;
        
        if (hitpoints == 1 && maxHitpoints>1) {
            color = Color.ORANGE; //color of bricks when 1 hit away become orange
        }
        
        if (hitpoints<=0) {
            destroyed = true;
        }
    }
    
    // set methods
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
    
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }
    
    // get methods
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public int getHitpoints() {
        return hitpoints;
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getPointValue() { 
        return pointValue; 
    }
    
}