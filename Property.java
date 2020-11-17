package BitSim;

/**
 * @author Luna Dagci
 * This class demonstrates both has-a and is-a relationship. Property is a child of Assets
 * */
public class Property extends Assets{
    String type; //type of property

    /**
     * Constructor for property
     * @param type of property
     * @param cost of property*/
    Property(String type, int cost){
        this.type = type;
        this.cost = cost;
    }

    /**
     * Getter for type
     * @return type*/
    public String getType() {
        return type;
    }

    /**
     * Getter for cost from parent
     * @return cost*/
     @Override
    public int getCost() {
        return cost;
    }

    /**String representation of property*/
    @Override
    public String toString() {
        return
                type + ", cost =" + cost;
    }
}
