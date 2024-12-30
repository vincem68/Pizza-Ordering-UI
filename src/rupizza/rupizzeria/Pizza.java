package rupizza.rupizzeria;

import java.util.ArrayList;

/**
 * This is an abstract class that defines what data fields and methods are
 * required by its subclasses.
 *
 * The Pizza abstract class has three subclasses: Pepperoni, Hawaiian, and
 * Deluxe.
 *
 * Each of these subclasses that extend from Pizza must have an array list of
 * toppings, a size, and a price() method that determines the price of that
 * Pizza type.
 *
 * @author Sawyer Reis, Vincent Mandola
 */
public abstract class Pizza {
	protected ArrayList<Topping> toppings = new ArrayList<>();
    protected Size size;

    public static final double additionalToppings = 1.79;
    public static final double pepperoniSmallPrice = 8.99;
    public static final double pepperoniMediumPrice = 10.99;
    public static final double pepperoniLargePrice = 12.99;
    public static final int numPepperoniToppings = 1;
    public static final double hawaiianSmallPrice = 10.99;
    public static final double hawaiianMediumPrice = 12.99;
    public static final double hawaiianLargePrice = 14.99;
    public static final int numHawaiianToppings = 2;
    public static final double deluxeSmallPrice = 12.99;
    public static final double deluxeMediumPrice = 14.99;
    public static final double deluxeLargePrice = 16.99;
    public static final int numDeluxeToppings = 5;
    public static final int maxPizzaToppings = 7;

    public ArrayList<Topping> getToppings(){
        return toppings;
    }
    public Size getSize() {
        return size;
    }

    public void setToppings(ArrayList<Topping> updatedToppings){
        this.toppings = updatedToppings;
    }

    public void setSize(String updatedSize){
        if(updatedSize.equals("small")){
            this.size = Size.small;
        }
        else if(updatedSize.equals("medium")){
            this.size = Size.medium;
        }
        else{
            this.size = Size.large;
        }
    }

    /**
     * This is an abstract method which means that it must be included in all
     * subclasses of this Pizza abstract class.
     *
     * This method has no body since it is abstract.
     *
     * @return nothing. This abstract never returns because all it does is
     * define a required method for subclasses of the Pizza abstract class.
     */
    public abstract double price();
}
