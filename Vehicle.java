package BitSim;

/**
 * @author Luna Dagci
 * This class demonstrates both has-a and is-a relationship. Property is a child of Assets
 * */
public class Vehicle extends Assets{
    String brand; //brand of car

    /**Getter for brand
     * @return brand*/
    public String getBrand() {
        return brand;
    }

    /**Getter for cost
     * @return cost*/
    public int getCost() {
        return cost;
    }

    /**
     * Constructor for vehicle
     * @param brand of car
     * @param cost of car*/
    Vehicle(String brand, int cost) {
        this.brand = brand;
        this.cost = cost;
    }

    /**
     * Strşng representation of vehicle*/
    @Override
    public String toString() {
        return "brand=" + brand +
                ", cost = " + cost;
    }
}
