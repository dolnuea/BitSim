package BitSim;

import javafx.application.Platform;

import java.util.Random;

/**
 * @author Luna Dagci
 * This class consists of activity functions. Demonstrates has a relationship for the sim.*/
public class Activity {

    Random random_flag = new Random(); //random object for randomizing events

    /**
     * Default Constructor*/
    Activity(){
    }

    /**
     * Sim goes to vacation to Hawaii*/
    String vacation(){
        Sim.happiness += 0.5; //when a sim goes on a vacation, their happiness increases
        Sim.bankbalance -=2000;
        return "I went to Oah, Hawaii for vacation. It was so relaxing\n";
    }

    /**
     * Sims want to have a plastic surgery. Plastic surgery sometimes can have circumstances. Beauty is pain*/
    boolean plasticSurgery(){
        boolean result = random_flag.nextBoolean();
       Sim.bankbalance -=1400;
        if (result) {
            Sim.looks = 1;
            Sim.happiness += 0.3;
        }
        else {
            Sim.looks = 0;
            Sim.happiness -= 0.6;
            if (Sim.happiness < 0) Sim.happiness = 0; //To avoid negative values in progress bar
        }
        return result;
    }
    /**
     * Sim gets their license. Every sim needs a license to buy a car*/
    String licenses(){
        return "Piece of cake! I got my license\n";
    }
    /**
     * Sim goes to salon and spa.*/
    String salonAndSpa(){
        //money down, looks up
        Sim.happiness += 0.4;
        Sim.bankbalance -=500;
        Sim.looks += 0.3;
        return "I got my nails done today and had a swedish full body massage\n";
    }
    /**
     * Sim goes to movie theater*/
    String movieTheater(){
        Sim.bankbalance -= 50;
        Sim.happiness += 0.5;
        return "I went to watch Borat 2. It was hilarious. Wawaweewa\n";
    }
    /**
     * Sim does a relaxing activity for their soul*/
    String mindAndBody(){
        Sim.happiness += 0.3;
        Sim.health += 0.3;
        return "I hiked for 3 hours around the Appalachian Mountains\n";
    }
    /**
     * Sims ages. The main activity of the game*/
    String age(){
        Sim.age++;
        Sim.bankbalance += Sim.occupation.getSalary();
        Sim.sick = random_flag.nextBoolean();
        if (Sim.sick) Sim.health -= 0.5;
        if (Sim.health < 0) Sim.health = 0; //To avoid negative values in progress bar
        if (Sim.occupation.getTitle().equals("Student") && Sim.education.getYearsLeft() != 0)
            Sim.education.yearsLeft--;
        return Sim.age + " years old: \n";
    }
    /**
     * Exit game.
     * Sim ends their life*/
    void surrender(){
        Platform.exit();
    }
}
