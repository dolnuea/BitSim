package BitSim;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorLabel;
    @FXML
    private Button login;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException, SQLException {

        if (username.getText().isEmpty() || password.getText().isEmpty()){
            errorLabel.setText("Empty credentials");
        }
        else {
            errorLabel.setText("");
            validateLogin();
        }
    }
    /**
     * Ask for credentials helper function*/
    private void validateLogin() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        boolean success = false;
        if (connectDB != null) {
            System.out.println("Database is Successfully Connected");
            /*The try statement ensures that each resource is closed at the end of the statement: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html*/
            Statement statement = connectDB.createStatement();
            try {
                ResultSet queryResult = statement.executeQuery("SELECT * FROM UserAccount AS u WHERE u.username = '" + username.getText() + "' AND u.password = '" + password.getText() + "'");
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        success = true;
                        buildGameMenu();
                    } else {
                        success = false;
                    }
                }
                if (!success)
                    errorLabel.setText("Invalid credentials");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            } finally {
                statement.close();
            }
        } else System.out.println("Database Connection Failed");
    }
    private void buildGameMenu() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) login.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("GameMenu.fxml")); //load the game fxml file
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo
    }
}
