package rupizza.rupizzeria;

public class PizzaMaker {
    public static Pizza createPizza(String flavor){
        if(flavor.equals("pepperoni")){
            Pepperoni newPepperoni = new Pepperoni();
            newPepperoni.toppings.add(Topping.pepperoni);
            newPepperoni.size = Size.small;
            return newPepperoni;
        }
        else if(flavor.equals("hawaiian")){
            Hawaiian newHawaiian = new Hawaiian();
            newHawaiian.toppings.add(Topping.pineapple);
            newHawaiian.toppings.add(Topping.ham);
            newHawaiian.size = Size.small;
            return newHawaiian;
        }
        else if(flavor.equals("deluxe")){
            Deluxe newDeluxe = new Deluxe();
            newDeluxe.toppings.add(Topping.bacon);
            newDeluxe.toppings.add(Topping.onion);
            newDeluxe.toppings.add(Topping.chicken);
            newDeluxe.toppings.add(Topping.mushrooms);
            newDeluxe.toppings.add(Topping.sausage);
            newDeluxe.size = Size.small;
            return newDeluxe;
        }
        else{
            return null;
        }
    }
}