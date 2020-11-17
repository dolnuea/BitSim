package BitSim;

import java.util.Random;

/**
 * @author Luna Dagci
 * This class also demonstrates has-a and is-a relationship. Job is a type of Occupation and every sim can have
 * a job.*/
public class Job implements Occupation {

    protected String title; //job title
    protected int salary; //job salary

    /**
     * Setter for title*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for salary*/
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Getter for title*/
    public String getTitle() {
        return title;
    }

    /**
     * getter for salary*/
    public int getSalary() {
        return salary;
    }

    /**
     * Job constructor
     * @param title of the job
     * @param salary of the job */
    Job(String title, int salary){
    this.title = title;
    this.salary = salary;
    }

    /**
     * This function is similar to an Activity function but it is special for Job
     * Sim asks for raise from their job, raise is approved randomly*/
    boolean raise(){
    Random random = new Random();
    boolean result = random.nextBoolean();
    if (result) {
        salary += 2000;
        Sim.happiness += 0.4;
        }
    return result;
    }

    /**
     * This function is similar to an Activity function but it is special for Job
     * Sim want to quit job. Sim obtains the new occupation as an unemployed*/
    void resign(){
    Sim.occupation = new Job("Unemployed", 0);
    }

    /**
     * String representation of Job
     * */
    @Override
    public String toString() {
        return
                title +
                ", salary $" + salary;
    }
}
