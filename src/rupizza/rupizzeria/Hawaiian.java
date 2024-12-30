package rupizza.rupizzeria;

import java.util.ArrayList;

/**
 * This class represents an instance of a Hawaiian Pizza.
 *
 * Each Hawaiian Pizza has an array list of toppings that contains at least
 * pineapple and ham.
 *
 * Each Hawaiian Pizza also has a size that represents whether the pizza is
 * small, medium, or large.
 *
 * @author Sawyer Reis, Vincent Mandola
 */
public class Hawaiian extends Pizza {


    /**
     * This method calculates and returns the price of a given instance of a
     * Hawaiian Pizza.
     *
     * Price is determined based on the size of the pizza and the number of
     * additional toppings.
     *
     * A small Hawaiian Pizza starts out at $10.99. Each subsequent size
     * increase will add $2.00. This means that a medium Hawaiian Pizza will
     * cost $12.99 and a large Hawaiian Pizza will cost $14.99.
     *
     * Any toppings on the Hawaiian Pizza besides pineapple or ham are
     * considered to be additional toppings. Each additional topping costs
     * $1.79.
     *
     * After adding the price for the size of the pizza together with the
     * price for each additional topping, we have the final price.
     *
     * @return the final price of the given instance of a Hawaiian Pizza.
     */
    public double price(){
    	double finalPrice = 0.00;
        if(this.size.equals(Size.small)){
            finalPrice = hawaiianSmallPrice;
        }
        else if (this.size.equals(Size.medium)){
            finalPrice = hawaiianMediumPrice;
        }
        else if (this.size.equals(Size.large)){
            finalPrice = hawaiianLargePrice;
        }
        finalPrice = finalPrice + ((this.toppings.size() -
                     numHawaiianToppings) * additionalToppings);
        return finalPrice;
    }
}