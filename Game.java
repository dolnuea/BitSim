package BitSim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Luna Dagci
 * The main class to start the game, beginning with the game menu which creates a new game or loads game from file directory.
 * This program has been coded in Java version 1.8.
 * */
public class Game extends Application {
    Scene scene1;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent menu = FXMLLoader.load(getClass().getResource("GameMenu.fxml")); //load menu fxml file
        primaryStage.setTitle("BitSim");
        scene1 = new Scene(menu);
        primaryStage.setScene(scene1); //set scene to menu
        primaryStage.show(); //display stage
    }
    public static void main(String[] args) throws Exception{
        launch(args);
    }
}