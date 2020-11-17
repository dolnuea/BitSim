package BitSim;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luna Dagci
 * This is the controller class for the game screen. There are a variety of in-game functions, specifically Surrender means
 * to die. However, in this program, Surrender is used to exit the game. This program can save and load games using text
 * files. The manage assets can remove property and vehicle objects from you assets.
 * */
public class InGameController implements Initializable {

    @FXML
    private Label bank_balance;
    @FXML
    private Label fullname;
    @FXML
    private TextArea story;
    @FXML
    private ProgressBar happiness;
    @FXML
    private ProgressBar health;
    @FXML
    private ProgressBar smarts;
    @FXML
    private ProgressBar looks;
    @FXML
    private ChoiceBox<Job> jobBox;
    @FXML
    private ChoiceBox<Education> educationBox;
    @FXML
    private ChoiceBox<Property> propertiesBox;
    @FXML
    private ChoiceBox<Property> sellPropertiesBox;
    @FXML
    private ChoiceBox<Vehicle> vehiclesBox;
    @FXML
    private ChoiceBox<Vehicle> sellVehiclesBox;
    @FXML
    private Label income;
    @FXML
    private Label occupation;


    Activity activity = new Activity(); //Activity object to access activity functions
    Random random = new Random(); //Random object used for random age events
    boolean result; //boolean answer data type for accepting answer from message boxes

    /**
     * Handler for age*/
    @FXML
    private void handleAgeButtonAction(ActionEvent event) {
        story.appendText(activity.age());
        story.appendText("\n");  //this will trigger the listener and will scroll the TextArea to the bottom
        bank_balance.setText(String.valueOf(Sim.bankbalance));

        if (Sim.sick) {
            story.appendText("I feel sick these days...\n");
            health.setProgress(Sim.health);
        }

        if(Sim.happiness <= 0){
        story.appendText("I am suffering from depression\n");
        }

        if (Sim.age == random.nextInt(100))
            story.appendText("I did not take a shower for 32 days\n");

        if (Sim.age == random.nextInt(100))
            story.appendText("The sky is very clear today\n");

        if (Sim.age == random.nextInt(100))
            story.appendText("I gave banana to a squirrel today\n");

        if (Sim.age == random.nextInt(100))
            story.appendText("I feel so tired these days\n");

        if (Sim.age == random.nextInt(100))
            story.appendText("Today, I heard a sound while I was the only one at home.\n");

        if (Sim.age == random.nextInt(100))
            story.appendText("What is the meaning of life?\n");
        if (Sim.age == 4) {
            result = messageBox("Uh Oh", "Your mom is accusing you of breaking her antique vase", "It was me:(", "I didn't do it!");
            if (result)
                story.appendText("I told her the truth.\n");
            else story.appendText("I lied to my mom...\n");
        }
        if (Sim.age == 6) {
            story.appendText("You started elementary school.\n");
            Sim.education.setMajor("Elementary School");
            Sim.occupation = new Job("Student", 0);
            occupation.setText(Sim.occupation.getTitle());
        }
        if (Sim.age == 12) {
            story.appendText("You started middle school.\n");
            Sim.education.setMajor("Middle School");
        }
        if (Sim.age == 12){
            if (Sim.gender.equals("Female")) {
                story.appendText("A boy tried to kiss me\n");
                result = messageBox("First kiss", "A boy is trying to kiss you!", "Push him", "Kiss him");
                if (result)
                    story.appendText("I pushed a boy who tried to kiss me\n");
                else story.appendText("I had my first kiss\n");
            }
            else if (Sim.gender.equals("Male")){
                story.appendText("A girl tried to kiss me\n");
                result = messageBox("First kiss", "A girl is trying to kiss you!", "Push her", "Kiss her");
                if (result) story.appendText("I pushed a girl who tried to kiss me\n");
                else story.appendText("I had my first kiss\n");
            }
        }
        if (Sim.age == 14) {
            story.appendText("You started high school.\n");
            Sim.education.setMajor("High School");
        }
        if (Sim.age == 15) {
            result = messageBox("You have to confront your sexuality", "Are you...?", "Gay/Bi", "Straight");
            if (result)
                story.appendText("I am Gay/Bisexual.\n");
            else story.appendText("I am straight\n");
        }
        if (Sim.age == 17) {
            result = messageBox("Drugs", "Someone just offered you weed", "Smoke weed", "Drugs aren't cool");
            if (result)
                story.appendText("Smoke weed everyday \uD83D\uDE0E\n");
            else story.appendText("Drugs are bad for your health and it is illegal >:(\n");
        }
        if (Sim.age == 18) {
            story.appendText("You have graduated from high school.\n Look for jobs or enroll in college\n");
            Sim.education = new Education("",0,0);
            Sim.occupation = new Job("Unemployed",0);
            occupation.setText(Sim.occupation.getTitle());
        }
        if (Sim.age >= 18 && Sim.occupation.getTitle().equals("Student")){
            story.appendText("I have "+ Sim.education.getYearsLeft() + " years left to finish college\n");
        }
        if (Sim.age >= 18 && Sim.occupation.getTitle().equals("Student") && Sim.education.getYearsLeft() == 0){
            Sim.occupation = new Job("Unemployed",0);
            occupation.setText(Sim.occupation.getTitle());
            story.appendText("I have graduated from " + Sim.education.getMajor() + " School\n");
        }
        if (Sim.age >= 100) {
            result = messageBox("A message from Grim Reaper", "You lived a long life;)", "Try to run", "Travel to Eternal World");
            story.appendText("Rest in peace...\n");
            activity.surrender();
        }
    }

    /**
     * Handler for mind and body*/
    @FXML
    private void handleMindBody(ActionEvent event) {
        if (Sim.age >= 10) { //some activities have age limits. Obviously, some ages are too young to do these activities
            story.appendText(activity.mindAndBody());
            health.setProgress(Sim.health);
            happiness.setProgress(Sim.happiness);
        } else story.appendText("I am too young to take a walk alone\n");
    }

    /**
     * Handler for salon and spa*/
    @FXML
    private void handleSalonSpa(ActionEvent event) {
        if (Sim.age >= 14) {
            if (Sim.bankbalance >= 500) {
                result = messageBox("Spa", "Salon and Spa costs $500\n", "Do it", "No");
                if (result)
                    story.appendText(activity.salonAndSpa());
                happiness.setProgress(Sim.happiness);
                happiness.setProgress(Sim.looks);
                bank_balance.setText(String.valueOf(Sim.bankbalance));
            } else story.appendText("I could not get salon and spa tine because I am broke. It costs $500\n");
        } else story.appendText("I am too young for that\n");
    }

    /**
     * Handler for the doctor*/
    @FXML
    private void handleDoctor(ActionEvent event) {
        result = messageBox("Doctor", "Get Treated? ($200 without insurance)", "Do it", "No");
            if (Sim.age >= 18 && Sim.bankbalance > 200){
            Sim.bankbalance -= 200;
                diagnose();
            } else if (Sim.age >= 18 && Sim.bankbalance < 200)
                story.appendText("I can't afford it.\n");
            if (Sim.age < 18) { //minors always have insurance and they do not need to pay
                diagnose();
            }
    }

    /**
     * Helper function for the handler for doctor
     * Doctor diagnoses the sim, patient*/
    private void diagnose() {
        boolean cured;
        if (Sim.sick) {
            story.appendText("I was diagnosed as sick\n");
            if (result) {
                cured = random.nextBoolean();
                if (cured) {
                    story.appendText("I got cured from sickness\n");
                    Sim.health += 0.6;
                    Sim.happiness += 0.4;
                    happiness.setProgress(Sim.happiness);
                    health.setProgress(Sim.health);
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                } else story.appendText("I am not cured from my sickness\n");
            } else story.appendText("I changed my mind\n");
        } else story.appendText("Doctor said I am healthy\n");
    }

    /**
     * Handler for movie theater*/
    @FXML
    private void handleMovieTheater(ActionEvent event) {
        if (Sim.age >= 12) {
            result = messageBox("Movies", "A movie ticket costs $50", "Do it", "No");
            if (result) {
                if (Sim.bankbalance >= 50) {
                    story.appendText(activity.movieTheater());
                    happiness.setProgress(Sim.happiness);
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                } else story.appendText("I don't have enough money\n");
            } else story.appendText("I changed my mind about going to movie theater.\n");
        }else story.appendText("I am too young to go alone\n");
    }

    /**
     * Handler for plastic surgery*/
    @FXML
    private void handlePlasticSurgery(ActionEvent event) {
        boolean success;
        if (Sim.age >= 18) {
            if (Sim.bankbalance >= 1400) {
                result = messageBox("Potato Head", "Plastic surgery costs $1400", "Do it!", "No");
                if (result) {
                    success = activity.plasticSurgery();
                    if (!success) {
                        story.appendText("My plastic surgery was a fail\nDamn it! I am going to sue that doctor.\n");
                    } else {
                        story.appendText("I got a successful plastic surgery\n");
                    }
                    happiness.setProgress(Sim.happiness);
                    looks.setProgress(Sim.looks);
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                } else story.appendText("I changed my mind about getting plastic surgery\n");
            } else story.appendText("I could not get plastic surgery because I am broke. It costs $1400\n");
        } else story.appendText("I am too young for that\n");
    }

    /**
     * Handler for vacation*/
    @FXML
    private void handleVacation(ActionEvent event) {
        if (Sim.age >= 18) {
            if (Sim.bankbalance >= 2000) {
                result = messageBox("Bon Voyage", "Vacation costs $1000", "Do it", "No");
                if (result) {
                    story.appendText(activity.vacation());
                    happiness.setProgress(Sim.happiness);
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                } else story.appendText("I changed my mind about going on a vacation\n");
            } else story.appendText("I can't go to vacation. I am broke. I need $2000\n");
        } else story.appendText("I am too young for that\n");
    }

    /**
     * Handler for licenses*/
    @FXML
    private void handleLicenses(ActionEvent event) {
        if (Sim.age >= 16) {
            if (!Sim.license) {
                boolean answer = messageBox("Get your License!", "What does a flashing yellow light mean?", "Proceed with caution", "Come to a full stop");
                if (answer) {
                    Sim.license = true;
                    story.appendText(activity.licenses());
                } else story.appendText("I failed the driving test\n");
            } else story.appendText("I already have a driving license\n");
        } else story.appendText("I am too young for that\n");
    }

    /**
     * Handler for surrender*/
    @FXML
    private void handleSurrender(ActionEvent event) throws InterruptedException {
        story.appendText("I have surrendered x_x. Goodbye World\n");
        Platform.exit();
    }

    /**
     * Handler for Job application*/
    @FXML
    private void handleApplyJob(ActionEvent event) {
        if (Sim.age >= 18) {
            if (!Sim.occupation.getTitle().equals("Student")) {
                result = messageBox("Interview", "What is your favorite marvel character?", "Spiderman", "Batman");
                if (!result) {
                    story.appendText("I got hired as a " + jobBox.getValue().toString() + "\n");
                    Sim.occupation = jobBox.getValue();
                    occupation.setText(Sim.occupation.getTitle());
                    income.setText(String.valueOf(Sim.occupation.getSalary()));
                    Sim.happiness += 0.5;
                } else {
                    story.appendText("I got rejected for job\n");
                    Sim.happiness -= 0.4;
                    if (Sim.happiness < 0) Sim.happiness = 0; //To avoid negative values in progress bar
                }
                happiness.setProgress(Sim.happiness);
            } else story.appendText("I cannot work while I am a student. It's too much stress!!");
        }else story.appendText("I am too young for that\n");
    }
    /**
     * Handler for admission to college
    * */
    @FXML
    private void handleApplyEducation(ActionEvent event) {
        if (Sim.age >= 18) {
            if(Sim.occupation.getTitle().equals("Unemployed")) {
                if (Sim.bankbalance >= educationBox.getValue().tuition) {
                    Sim.education = educationBox.getValue();
                    story.appendText("I got enrolled in " + educationBox.getValue().toString() + "\n");
                    Sim.occupation = new Job("Student",0);
                    Sim.bankbalance -= educationBox.getValue().tuition;
                    occupation.setText(Sim.occupation.getTitle());
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                } else story.appendText("I am broke\n");
            } else story.appendText("I need to finish or quit what I am occupied with first!\n");
        } else story.appendText("I need to finish K-12 first.\n");
    }
    /**
     * Handler for buying properties*/
    @FXML
    private void handleBuyProperty(ActionEvent event) {
        if (Sim.age >= 18) {
            if (Sim.bankbalance >= propertiesBox.getValue().cost) {
                Assets.properties.add(propertiesBox.getValue());
                story.appendText("I bought a " + propertiesBox.getValue().toString() + "\n");
                Sim.bankbalance -= propertiesBox.getValue().cost;
                bank_balance.setText(String.valueOf(Sim.bankbalance));
                sellPropertiesBox.getItems().add(propertiesBox.getValue());
            } else story.appendText("I could not get the house I wanted because I'm broke.\n");
        } else story.appendText("I am too young for that\n");
    }
    /**
     * Handler for selling properties*/
    @FXML
    private void handleSellProperty(ActionEvent event) {
        Property property = sellPropertiesBox.getValue();
        Assets.removeProperties(property);
        sellPropertiesBox.getItems().remove(property);
        Sim.bankbalance += property.getCost();
        bank_balance.setText(String.valueOf(Sim.bankbalance));
        story.appendText("I sold my " + property.getType() + " house.\n");
    }
    /**
     * Handler for buying vehicles*/
    @FXML
    private void handleBuyVehicle(ActionEvent event) {
        if (Sim.age >= 18) {
            if (Sim.license) {
                if (Sim.bankbalance >= vehiclesBox.getValue().cost) {
                    Assets.vehicles.add(vehiclesBox.getValue());
                    story.appendText("I bought a " + vehiclesBox.getValue().toString() + "\n");
                    Sim.bankbalance -= vehiclesBox.getValue().cost;
                    bank_balance.setText(String.valueOf(Sim.bankbalance));
                    sellVehiclesBox.getItems().add(vehiclesBox.getValue());
                } else story.appendText("I could not get the car I wanted because I'm broke.\n");
            } else story.appendText("I don't have driving license\n");
        } else story.appendText("I am too young for that\n");
    }
    /**
     * Handler for selling vehicles*/
    @FXML
    private void handleSellVehicle(ActionEvent event){
        Vehicle vehicle = sellVehiclesBox.getValue();
        sellVehiclesBox.getItems().remove(vehicle);
        Assets.removeVehicles(vehicle);
        Sim.bankbalance += vehicle.getCost();
        bank_balance.setText(String.valueOf(Sim.bankbalance));
        story.appendText("I sold my " + vehicle.getBrand() + " car.\n");
    }
    /**
     * Handler for requesting raise at a job
     * Sim asks for raise from their boss*/
    @FXML
    private void handleRaise(ActionEvent event) {
        if (Sim.occupation.getTitle().equals("Unemployed")) {
            story.appendText("I need a job for that first\n");
        } else {
            result = Sim.occupation.raise();
            if (result) {
                story.appendText("I got a $2000 raise from my job!:)\n");
                Sim.occupation.salary += 2000;
                income.setText(String.valueOf(Sim.occupation.getSalary()));
                Sim.happiness += 0.5;
            } else {
                story.appendText("Boss rejected my request for a raise after all the hard work I've done...\n");
                Sim.happiness -= 0.3;
                if (Sim.happiness < 0) Sim.happiness = 0; //To avoid negative values in progress bar
            }
            happiness.setProgress(Sim.happiness);
        }
    }
    /**
     * Handler for Job resign: Sim quits job*/
    @FXML
    private void handleResign(ActionEvent event) {
        if (Sim.occupation.getTitle().equals("Unemployed")) {
            story.appendText("I need a job for that first\n");
        } else {
            result = messageBox("Quit Job", "Are you sure you want to quit job?", "Yes", "No");
            if (result) {
                Sim.occupation.resign();
                story.appendText("I quit my job\n");
                Sim.occupation = new Job("Unemployed",0);
                occupation.setText(Sim.occupation.getTitle());
                income.setText(String.valueOf(Sim.occupation.getSalary()));
                Sim.happiness += 0.2;
                happiness.setProgress(Sim.happiness);
            }
        }
    }
    /**
     * Handler for game file saving*/
    @FXML
    private void handleSaveLife(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showSaveDialog(null);
        selectedFile.createNewFile(); //creates a new life with specified name
        SaveFile(Sim.info(), selectedFile);
    }
    /**
     * Helper function to save a file
     * @param content to save in the file
     * @param file file to save the content in*/
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static boolean answer; //answer for messageBox function
    /**
     * Helper function
     * Message box for random game choices
     * @param title title of the message box
     * @param message message in the message box
     * @param button1 choice one
     * @param button2 choice two
     * @return Boolean for answer true or false*/
    private static Boolean messageBox(String title, String message, String button1, String button2){

        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(500);
        Label label = new Label();
        label.setText(message);
        Button yes_button = new Button(button1);
        Button no_button = new Button(button2);

        //listener for choice buttons
        yes_button.setOnAction(e -> {
            answer = true;
            window.close();
        });
        no_button.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes_button, no_button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
    /**
     * Initialize everything beforehand*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fullname.setText(Sim.firstname + " " + Sim.lastname);
        bank_balance.setText(Integer.toString(Sim.bankbalance));
        happiness.setProgress(Sim.happiness);
        health.setProgress(Sim.health);
        smarts.setProgress(Sim.smarts);
        looks.setProgress(Sim.looks);
        occupation.setText(Sim.occupation.getTitle());
        bank_balance.setText(String.valueOf(Sim.bankbalance));
        income.setText(String.valueOf(Sim.occupation.getSalary()));

        //Enables the story text area to scroll down as story goes on
        story.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> story.setScrollTop(Double.MAX_VALUE));
       if (Sim.age == 0)
        story.appendText("Age " + Sim.age + ":\n You were born at St. Peter's Hospital, Albany, NY, USA\n");
       else story.appendText("Age " + Sim.age + ":\n I slept like a vampire today. I woke up to the sunshine beaming onto my face.\n");

        //fill up choice boxes
        ObservableList<Job> jobs = FXCollections.observableArrayList();
        jobs.add(new Job("Bartender", 50000));
        jobs.add(new Job("Cameraman", 52654));
        jobs.add(new Job("Business Analyst", 78114));
        jobs.add(new Job("Junior Sous Chef",60180));
        jobs.add(new Job("Artist", 70000));
        jobBox.setItems(jobs);

        ObservableList<Education> colleges = FXCollections.observableArrayList();
        colleges.add(new Education("Business", 10000,  4));
        colleges.add(new Education("Art", 15000, 4));
        colleges.add(new Education("Math", 16200, 4));
        colleges.add(new Education("Culinary", 20000,  4));
        colleges.add(new Education("Biology", 30000, 4));
        educationBox.setItems(colleges);

        ObservableList<Property> properties = FXCollections.observableArrayList();
        properties.add(new Property("Row House", 558272));
        properties.add(new Property("Lodge", 426181));
        properties.add(new Property("Manufactured Home", 44759));
        properties.add(new Property("Condo", 115717));
        properties.add(new Property("Bungalow", 262751));
        propertiesBox.setItems(properties);

        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
        vehicles.add(new Vehicle("Kia Optima",24668));
        vehicles.add(new Vehicle("Smart Fortwo",25772));
        vehicles.add(new Vehicle("Hyundai Santa Fe",33297));
        vehicles.add(new Vehicle("Porche Boxter",62079));
        vehicles.add(new Vehicle("Ferrari Portofino",953143));
        vehiclesBox.setItems(vehicles);
    }
}
