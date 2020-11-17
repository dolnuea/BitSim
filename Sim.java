package BitSim;

/**
 * @author Luna Dagci
 * This class is the main focus of this game. Every game has only one sim character. Therefore static objects define that
 * there can be only one Sim object. This class is also used to load and save game files with function String info()*/
public class Sim {
    /*All fields are static since this program has one and only one Sim character*/
    static String firstname;
    static String lastname;
    static String gender;
    static int age = 0; //baby as default
    static int bankbalance = 0;
    static Education education = new Education("", 0, 0);
    static Job occupation = new Job("Unemployed", 0);
    static float happiness;
    static float health;
    static float smarts;
    static float looks;
    static boolean license = false;
    static  boolean sick = false;


    /**
     * Static String function which stores the information of the sim
     * This function will be used to save game files.*/
    public static String info() {
        return
                firstname + '\n' +
                lastname + '\n' +
                gender + '\n' +
                age + "\n" +
                bankbalance + "\n" +
                education.getMajor() + '\n' +
                occupation.getTitle() + '\n' +
                occupation.getSalary() + '\n' +
                happiness + "\n" +
                health + "\n" +
                smarts + "\n" +
                looks;
    }
}