package BitSim;

/**
 * @author Luna Dagci
 * This class also demonstrates has-a and is-a relationship. Education is a type of Occupation and every sim can have
 * an education.*/
public class Education implements Occupation {
    String major;
    int tuition;
    int yearsLeft = 12; //default value for K-12 Education

    /**
     * Education constructor
     * @param major initialize field of study
     * @param tuition cost of school
     * @param yearsLeft number of years left to finish school*/
    public Education(String major, int tuition, int yearsLeft) {
        this.major = major;
        this.tuition = tuition;
        this.yearsLeft = yearsLeft;
    }

    /**
     * Getter for major
     * @return major
     * */
    public String getMajor() {
        return major;
    }

    /**
     * Setter for major
     * @param major takes argument major
     * Changes the value of major*/
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Getter for yearsLeft
     * @return number of years left to finish school*/
    public int getYearsLeft() {
        return yearsLeft;
    }

    /**
     * String representation of Education info*/
    @Override
    public String toString() {
        return major + " major " + ", tuition = " + tuition;
    }
}
