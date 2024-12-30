package rupizza.rupizzeria;

import java.util.ArrayList;

/**
 * This class represents an instance of a Pepperoni Pizza.
 *
 * Each Pepperoni Pizza has an array list of toppings that contains at least
 * pepperoni.
 *
 * Each Pepperoni Pizza also has a size that represents whether the pizza is
 * small, medium, or large.
 *
 * @author Sawyer Reis, Vincent Mandola
 */
public class Pepperoni extends Pizza{
    
	

    /**
     * This method calculates and returns the price of a given instance of a
     * Pepperoni Pizza.
     *
     * Price is determined based on the size of the pizza and the number of
     * additional toppings.
     *
     * A small Pepperoni Pizza starts out at $8.99. Each subsequent size
     * increase will add $2.00. This means that a medium Pepperoni Pizza will
     * cost $10.99 and a large Pepperoni Pizza will cost $12.99.
     *
     * Any toppings on the Pepperoni Pizza besides pepperoni itself are
     * considered to be additional toppings. Each additional topping costs
     * $1.79.
     *
     * After adding the price for the size of the pizza together with the
     * price for each additional topping, we have the final price.
     *
     * @return the final price of the given instance of a Pepperoni Pizza.
     */
    public double price(){
    	double finalPrice = 0.00;
        if(this.size.equals(Size.small)){
            finalPrice = pepperoniSmallPrice;
        }
        else if (this.size.equals(Size.medium)){
            finalPrice = pepperoniMediumPrice;
        }
        else if (this.size.equals(Size.large)){
            finalPrice = pepperoniLargePrice;
        }
        finalPrice = finalPrice + ((this.toppings.size() -
                     numPepperoniToppings) * additionalToppings);
        return finalPrice;
    }
}