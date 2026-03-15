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
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;


public class Main extends Application {
    
    private BorderPane root;
    private Canvas canvas;
    private GraphicsContext gc; 
    private int score;
    private int currentLives;
    private Circle[] livesCircles;
    private Label scoreDisplay;
    private HBox livesBox, scoreBox; 
    private ToggleGroup difficultyLevel;
    
    @Override
    public void start(Stage primaryStage) {
        
        root = new BorderPane();
        root.setMinSize(912, 712); // Ensures a minimum screen size
        
        // Menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quitItem = new MenuItem("Quit"); 
        quitItem.setOnAction((ActionEvent ev) -> Platform.exit());
        
        // Displays a pop-up when About is clicked
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About"); 
        
        Alert aboutAlert = new Alert(AlertType.INFORMATION); 
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Bashout");
        aboutAlert.setContentText("Objective: Score as many points as possible by destroying bricks. \n\n" + "By Swaraj, Aravind, Zaid and Kaveen.");
        aboutItem.setOnAction((ActionEvent ev) -> {aboutAlert.showAndWait();}); 
    
        
        fileMenu.getItems().add(quitItem);
        helpMenu.getItems().add(aboutItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        root.setTop(menuBar);

      

        // Start Game Engine
        showStartScreen();
        GameEngine engine = new GameEngine();
                
        // TODO - Set up keyboard event listeners on the Scene and pass them to GameEngine
        String level = getDifficultyLevel();
        //engine.start(gc, level);
        

        Scene scene = new Scene(root);
        primaryStage.setTitle("Bashout");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // TODO - Add the JavaFX Animation
    }
    
    private void showStartScreen(){
        // Start Screen
        VBox startScreen = new VBox(10);
        startScreen.setAlignment(Pos.CENTER);
        startScreen.setStyle("-fx-background-color: black;");
        
        Label title = new Label("B A S H O U T");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-font-family: 'Impact'; -fx-text-fill: #ff073a;");
        Glow glow = new Glow(0.8);  
        title.setEffect(glow);
        
        difficultyLevel = new ToggleGroup();
        RadioButton easyButton = new RadioButton("Easy");
        RadioButton hardButton = new RadioButton("Hard");
        easyButton.setStyle("-fx-font-size: 25px; -fx-font-family: 'Impact'; -fx-text-fill: #dddddd;");
        hardButton.setStyle("-fx-font-size: 25px; -fx-font-family: 'Impact'; -fx-text-fill: #dddddd;");
        easyButton.setToggleGroup(difficultyLevel);
        hardButton.setToggleGroup(difficultyLevel);
        easyButton.setSelected(true);
        
        HBox difficultyBox = new HBox(20);
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.getChildren().addAll(easyButton, hardButton);         
        
        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 20px; -fx-font-family: 'Impact'; -fx-background-color: #ff8c00; -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-border-color: #cc7000; -fx-border-width: 2px;");
        startButton.setOnAction((ActionEvent ev) -> showGameScreen());
        
        startScreen.getChildren().addAll(title, difficultyBox, startButton);
        startScreen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        startScreen.setSpacing(50);
        
        root.setCenter(startScreen);
    }
    
    private void showGameScreen(){
        
        StackPane canvasContainer = new StackPane();
        canvas = new Canvas(900, 700);
        gc = canvas.getGraphicsContext2D(); 
        canvasContainer.getChildren().add(canvas);
        canvasContainer.setStyle("-fx-border-color: #ff073a; -fx-background-color: black; -fx-border-width: 3px; -fx-padding: 3px;");  
        
        Button test = new Button("Game Over");
        //test.setOnAction((ActionEvent ev) -> showGameOverScreen());

        // Lives box in far left
        livesBox = new HBox(3);
        livesBox.setStyle("-fx-padding: 10;");
        
        for(int i = 0; i < 3; i++) {
            livesBox.getChildren().add(new Circle(10, Color.WHITE));
        }
        
        // Score box in centre 
        Label scoreDisplay = new Label("SCORE: 0");
        scoreDisplay.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-font-family: 'Impact'; -fx-text-fill: white; -fx-padding: 15;");
        scoreBox = new HBox(scoreDisplay);
        scoreBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(scoreBox, Priority.ALWAYS);    
        
        HBox topGameBar = new HBox(livesBox, scoreBox);
        topGameBar.setStyle("-fx-background-color: black; -fx-padding: 2 10 2 10;");
        
        // Ensures menu is displayed on game screen 
        MenuBar existingMenu = (MenuBar) root.getTop(); 
        VBox topContainer = new VBox(0);
        topContainer.getChildren().addAll(existingMenu, topGameBar);
    
        root.setTop(topContainer); 
        root.setCenter(canvasContainer);
        
        // Fits everything to tab size responsively 
        Platform.runLater(() -> {
        if(root.getScene() != null && root.getScene().getWindow() != null){
            root.getScene().getWindow().sizeToScene();
            root.getScene().getWindow().centerOnScreen(); 
            }
        });
    }
    
    private void loseLife(){
        currentLives--;
        
        if(!livesBox.getChildren().isEmpty()){
           livesBox.getChildren().remove(livesBox.getChildren().size() - 1); 
        }
        else{
            //showGameOverScreen();
            System.out.println("Game over");
        }
    }
    
    private void updateScore(int addScore){
        score += addScore;
        scoreDisplay.setText("SCORE: " + score);
    }
    
    private String getDifficultyLevel() {
        RadioButton selectedLevel = (RadioButton) difficultyLevel.getSelectedToggle();
        return(selectedLevel.getText());
    }

    public static void main(String[] args) {
        launch(args);
    }
}