import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class SpecialBrick extends Brick {
    private int hitsRemaining = 2; // Currently needs 2 hits to break, can change later accordingly
    
    private SpecialType type;
    private Random rand;
    public enum SpecialType {
        EXPLODING,
        POWERUP
    }
    
    public SpecialBrick(double x, double y, SpecialType type) {
        super(x, y, Color.PURPLE, 1);
        
        this.type = type;
        this.rand = new Random();
        this.pointValue = 300; // can change later depending on type of brick
        
        if (type == SpecialType.EXPLODING) {
            color = color.ORANGE;
        }
        
        if (type == SpecialType.POWERUP) {
            color = color.GREEN;
        }
    }

    @Override
    public void hit() {
        super.hit();
        
        if (destroyed == true) {
            if (type== SpecialType.EXPLODING) {
                explode();
            }
            
            if (type == SpecialType.POWERUP) {
                dropPowerup();
            }
            
        }
        // If hitsRemaining == 0, set isDestroyed = true.
    }
    
    private void explode() {
        System.out.println("Explosion");
        // NEED TO COMPLETE
    }
    
    private void dropPowerup() {
        int value = rand.nextInt(4);
        
        switch(value) {
            case 0:
                System.out.println("Expand paddle");
                break;
            
            case 1:
                System.out.println("Shrink paddle");
                break;
            
            case 2:
                System.out.println("Slow ball");
                break;
            
            case 3:
                System.out.println("Score multiplier");
                break;
        }
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        if (isDestroyed()==false) {
            gc.setFill(color);
            gc.fillRect(x,y,width,height);
            
            gc.setStroke(Color.BLACK);
            
            if (type == SpecialType.EXPLODING) {
                gc.strokeLine(x,y, x + width, y + height);
                gc.strokeLine(x+width, y, x, y + height);
            }
            
            if (type == SpecialType.POWERUP) {
                gc.strokeOval(x + width/4, y + height/4, width/2, height/2);
            }
        }
    }
}