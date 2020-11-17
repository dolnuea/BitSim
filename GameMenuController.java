package BitSim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * @author Luna Dagci
 * This is the controller class for the game menu.*/
public class GameMenuController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private ChoiceBox<String> genders;
    @FXML
    private Button newGame;
    @FXML
    private Button loadGame;

    /**
     * Handler for new game button from the FXML file*/
    @FXML
    private void handleNewGameAction(ActionEvent event) throws Exception {
        //This game can have only one Sim object. Therefore, that's why we use static variables
        Stage stage;
        Parent root;
        Random random = new Random();
        if (firstname.getText().isEmpty() || lastname.getText().isEmpty()) { //nameless sim cannot be accepted
            errorLabel.setText("Your sim is nameless!!!");
        } else {
            //create the game
            stage = (Stage) newGame.getScene().getWindow();
            Sim.firstname = firstname.getText();
            Sim.lastname = lastname.getText();
            Sim.gender = genders.getValue();
            Sim.happiness = random.nextFloat();
            Sim.health = random.nextFloat();
            Sim.smarts = random.nextFloat();
            Sim.looks = random.nextFloat(); //0.0-1.0

            root = FXMLLoader.load(getClass().getResource("InGame.fxml")); //load the game fxml file
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handler for load game button from the FXML file*/
    @FXML
    private void handleLoadButton(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
            //open file explorer, get file send to loadGame func
            stage = (Stage) loadGame.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            loadGame(selectedFile);
        root = FXMLLoader.load(getClass().getResource("InGame.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Helper function to load a game
     * @param file selected from user
     * @throws FileNotFoundException when file DNE*/
    private void loadGame(File file) throws FileNotFoundException {
        //read content of file and assign first name, age, gender, occupation, health, happiness, smarts, looks, education and bank balance
        Scanner scanner = new Scanner(file);
        List<String> sim_attributes = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String attribute = scanner.nextLine();
            sim_attributes.add(attribute);
        } //load game
        Sim.firstname = sim_attributes.get(0);
        Sim.lastname = sim_attributes.get(1);
        Sim.gender = sim_attributes.get(2);
        Sim.age = Integer.parseInt(sim_attributes.get(3));
        Sim.bankbalance = Integer.parseInt(sim_attributes.get(4));
        Sim.education.setMajor(sim_attributes.get(5));
        Sim.occupation.setTitle(sim_attributes.get(6));
        Sim.occupation.setSalary(Integer.parseInt(sim_attributes.get(7)));
        Sim.happiness = Float.parseFloat(sim_attributes.get(8));
        Sim.health = Float.parseFloat(sim_attributes.get(9));
        Sim.smarts = Float.parseFloat(sim_attributes.get(10));
        Sim.looks = Float.parseFloat(sim_attributes.get(11));
        scanner.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To do
    }
}
