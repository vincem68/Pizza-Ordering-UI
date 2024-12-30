package rupizza.rupizzeria;

import java.util.ArrayList;

/**
 * This class represents an instance of a Deluxe Pizza.
 *
 * Each Deluxe Pizza has an array list of toppings that contains at least
 * bacon, onion, chicken, mushrooms, and sausage.
 *
 * Each Deluxe Pizza also has a size that represents whether the pizza is
 * small, medium, or large.
 *
 * @author Sawyer Reis, Vincent Mandola
 */
public class Deluxe extends Pizza {
    
	
    /**
     * This method calculates and returns the price of a given instance of a
     * Deluxe Pizza.
     *
     * Price is determined based on the size of the pizza and the number of
     * additional toppings.
     *
     * A small Deluxe Pizza starts out at $12.99. Each subsequent size
     * increase will add $2.00. This means that a medium Deluxe Pizza will
     * cost $14.99 and a large Deluxe Pizza will cost $16.99.
     *
     * Any toppings on the Deluxe Pizza besides bacon, onion, chicken,
     * mushrooms, or sausage are considered to be additional toppings.
     * Each additional topping costs $1.79.
     *
     * After adding the price for the size of the pizza together with the
     * price for each additional topping, we have the final price.
     *
     * @return the final price of the given instance of a Deluxe Pizza.
     */
    public double price(){
    	double finalPrice = 0.00;
        if(this.size.equals(Size.small)){
            finalPrice = deluxeSmallPrice;
        }
        else if (this.size.equals(Size.medium)){
            finalPrice = deluxeMediumPrice;
        }
        else if (this.size.equals(Size.large)){
            finalPrice = deluxeLargePrice;
        }
        finalPrice = finalPrice + ((this.toppings.size() -
                     numDeluxeToppings) * additionalToppings);
        return finalPrice;
    }
}
