package BitSim;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Luna Dagci
 * Assets is the parent class of Property and Vehicle. This class store the assets of a sim. Demonstrates both
 * is-a for Property and Vehicle and has-a relationship for Sim*/
public class Assets {

    static List<Property> properties = new ArrayList<>(); //Stores property assets
    static List<Vehicle> vehicles = new ArrayList<>(); //Stores vehicle assets
    int cost;

    /**
     * The parent function. Every assets has a cost*/
    public int getCost() {
        return cost;
    }
    /**
     * Function to sell properties. Removes properties from the assets
     * @param property to be removed*/
    public static void removeProperties(Property property){
        properties.remove(property);
    }
    /**
     * Function to sell vehicles. Removes vehicles from the assets
     * @param vehicle to be removed*/
    public static void removeVehicles(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    /**
     * Prints the assets of a sim*/
    @Override
    public String toString() {
        return "Properties{" +
                "properties=" + properties.toString() +
                ", vehicles=" + vehicles.toString() +
                '}';
    }
}
