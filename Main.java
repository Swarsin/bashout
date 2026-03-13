import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Setup Menu Here 
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit"); // Quit option
        // TODO - Add action to quitItem to close the game
        
        // Displays a pop-up when About is clicked
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About"); 
        
        Alert aboutAlert = new Alert(AlertType.INFORMATION); 
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Bashout");
        aboutAlert.setContentText("Objective: Score as many points as possible by destroying bricks. \n\n" + "By: Swaraj, Aravind, Zaid, Kaveen");
        aboutItem.setOnAction((ActionEvent ev) -> {aboutAlert.showAndWait();}); 
    
        
        fileMenu.getItems().add(quitItem);
        helpMenu.getItems().add(aboutItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        root.setTop(menuBar);

        // Setup Canvas for Vector Graphics
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D(); // Used to draw everything
        //root.setCenter(canvas);

        // Start Game Engine
        GameEngine engine = new GameEngine();
        
        // Start Screen
        VBox startScreen = new VBox(10);
        startScreen.setAlignment(Pos.CENTER);
        
        Label title = new Label("BASHOUT");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Arial'; -fx-fill: red;");
        Glow glow = new Glow(0.8);  
        title.setEffect(glow);
        
        ToggleGroup difficultyLevel = new ToggleGroup();
        RadioButton easy = new RadioButton("Easy");
        RadioButton hard = new RadioButton("Hard");
        easy.setToggleGroup(difficultyLevel);
        hard.setToggleGroup(difficultyLevel);
        easy.setSelected(true);
        
        Button startButton = new Button("Start Game");
        startScreen.getChildren().addAll(title, easy, hard, startButton);
        startScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setCenter(startScreen);
        
        
        
        
        // TODO - Set up keyboard event listeners on the Scene and pass them to GameEngine
        engine.start(gc);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Bashout");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // TODO - Add the JavaFX Animation
    }

    public static void main(String[] args) {
        launch(args);
    }
}