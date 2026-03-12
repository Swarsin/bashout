import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Setup Menu Here 
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit"); // Quit option
        // TODO - Add action to quitItem to close the game
        
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About"); // Must display a pop-up
        // TODO - Add action to aboutItem to show an Alert with names/title and game information/instructions
        
        fileMenu.getItems().add(quitItem);
        helpMenu.getItems().add(aboutItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        root.setTop(menuBar);

        // Setup Canvas for Vector Graphics
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Used to draw everything
        root.setCenter(canvas);

        // Start Game Engine
        GameEngine engine = new GameEngine();
        // TODO - Set up keyboard event listeners on the Scene and pass them to GameEngine
        engine.start(gc);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Vector Breakout");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // TODO - Add the JavaFX Animation
    }

    public static void main(String[] args) {
        launch(args);
    }
}