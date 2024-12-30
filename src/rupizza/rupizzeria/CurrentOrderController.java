package rupizza.rupizzeria;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import java.text.DecimalFormat;
import java.util.StringTokenizer;




public class CurrentOrderController {

    private MainMenuController mainController;

    @FXML
    private TextField currentOrderPhone;

    @FXML
    private TextField currentOrderTotal;

    @FXML
    private TextField currentOrderSubtotal;

    @FXML
    private TextField currentOrderTax;

    @FXML
    private ListView<String> currentOrderListView;


    /**
     * This method removes a Pizza from the current Order
     * the user has. The description of the Pizza to be removed 
     * is removed from the ListView and the Pizza is also removed
     * from the Pizza ArrayList in the Order.
     * @param e - ActionEvent that occurs when the user selects a
     * Pizza in ListView and clicks the Remove button
     */
    @FXML
    void removePizza(ActionEvent e) {
        String removedPizza;
        removedPizza = currentOrderListView.getSelectionModel().getSelectedItem();
        int pizzaIndex = currentOrderListView.getSelectionModel().getSelectedIndex();
        ArrayList<Pizza> pizzas = mainController.currentOrder.getPizzas();
        if (removedPizza == null) {
            Alert noSelectionError = new Alert(Alert.AlertType.ERROR);
            noSelectionError.setTitle("No Selection Error");
            noSelectionError.setHeaderText(null);
            noSelectionError.setContentText("You must select a pizza to " +
                    "remove.");
            noSelectionError.showAndWait();
            return;
        }
        currentOrderListView.getItems().remove(removedPizza);
        pizzas.remove(pizzaIndex);
        setCurrentOrderScreen();
    }

    /**
     * This method adds an order to the Order ArrayList in 
     * StoreOrders. After placing the order, it clears the TextField that 
     * contains the phone number of the order, clears the current order 
     * and closes the window of the Current Order. It then sends an Alert 
     * letting the user know the order has been placed.
     * @param e - ActionEvent that occurs when the user clicks the 
     * Place Order button.
     */
    @FXML
    void placeOrder(ActionEvent e) {
        ArrayList<Order> orders = mainController.storeOrders.getOrders();
        Order order = mainController.currentOrder;
        orders.add(order);
        mainController.currentOrder = new Order(null, false);
        mainController.changeCustomerPhone();
        mainController.closeCurrentOrderWindow();
        currentOrderPhone.setText(null);
        Alert noSelectionError = new Alert(Alert.AlertType.INFORMATION);
        noSelectionError.setTitle("Order Placed");
        noSelectionError.setHeaderText(null);
        noSelectionError.setContentText("Congratulations! Your order has" +
                " been placed!");
        noSelectionError.showAndWait();
        return;
    }

    /**
     * This method takes the order information entered by the 
     * user in the Pizza Order windows and the Main Menu window 
     * stored in the current order object and sets the Current Order 
     * window by displaying the phone number of the order as well 
     * as each Pizza in the ListView, which includes the toppings, 
     * type of Pizza, and Size. The subtotal, sales tax and total 
     * are also displayed.
     */
    public void setCurrentOrderScreen(){
        if(!currentOrderListView.getItems().isEmpty()){
            currentOrderListView.getItems().clear();
        }
        currentOrderPhone.setText(mainController.currentOrder.getPhoneNum());
        DecimalFormat df = new DecimalFormat("0.00");
        double subtotal;
        subtotal = findSubtotal(mainController.currentOrder.getPizzas());
        currentOrderSubtotal.setText(df.format(subtotal));
        double tax = getTax(subtotal);
        currentOrderTax.setText(df.format(tax));
        double finalTotal = subtotal + tax;
        currentOrderTotal.setText(df.format(finalTotal));
        ArrayList<Pizza> pizzas = mainController.currentOrder.getPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            currentOrderListView.getItems().add(completeOrder(pizzas.get(i)));
        }

    }

    /**
     * This method goes through the ArrayList of Pizzas in the current 
     * order and calculates the price of each Pizza and adds each price
     * @param pizzas - Pizza ArrayList of the current order
     * @return - subtotal of all Pizza prices added together
     */
    private double findSubtotal(ArrayList<Pizza> pizzas) {
        double total = 0;
        for (int i = 0; i < pizzas.size(); i++) {
            total += pizzas.get(i).price();
        }
        return total;
    }

    /**
     * This method takes the subtotal of an order and returns
     * the sales tax that would be added to the order total
     * @param subtotal - subtotal of the current order
     * @return the sales tax of the order's subtotal
     */
    private double getTax(double subtotal) {
        return subtotal * 0.06625;
    }

    /**
     * This method lets us get access to all the variables 
     * and methods in MainMenuController
     * @param controller - instance of MainMenuController
     */
    public void setMainController(MainMenuController controller){
        mainController = controller;

    }

    /**
     * This method takes a Pizza object and goes through
     * it's Toppings ArrayList, takes its Size and price 
     * and puts all into a String and returns the String.
     * @param pizza - Pizza to make order String out of
     * @return - String that has all Toppings, size, kind 
     * and price of Pizza
     */
    private String completeOrder(Pizza pizza) {
        DecimalFormat df = new DecimalFormat("0.00");
        String fullPizza = "";
        String size = sizeToString(pizza.getSize());
        fullPizza += size + " ";
        ArrayList<Topping> toppings = pizza.getToppings();
        if (pizza instanceof Hawaiian) {
            fullPizza += "hawaiian pizza, ";
        }
        else if (pizza instanceof Pepperoni) {
            fullPizza += "pepperoni pizza, ";
        } else {
            fullPizza += "deluxe pizza, ";
        }
        for (int i = 0; i < toppings.size(); i++) {
            Topping topping = toppings.get(i);
            fullPizza += toppingsString(topping) + ",";
        }
        fullPizza += " $" + String.valueOf(df.format(pizza.price()));
        return fullPizza;
    }

    /**
     * This method returns a String representing a given 
     * Topping.
     * @param topping - Topping given from a Pizza
     * @return - String representation of the given Topping
     */
    private String toppingsString(Topping topping) {
        if (topping == Topping.pepperoni) {
            return " pepperoni";
        }
        else if (topping == Topping.chicken) {
            return " chicken";
        }
        else if (topping == Topping.ham) {
            return " ham";
        }
        else if (topping == Topping.pineapple) {
            return " pineapple";
        }
        else if (topping == Topping.bacon) {
            return " bacon";
        }
        else if (topping == Topping.onion) {
            return " onion";
        }
        else if (topping == Topping.mushrooms) {
            return " mushrooms";
        }
        else if (topping == Topping.anchovies) {
            return " anchovies";
        }
        else {
            return " sausage";
        }
    }

    /**
     * This method takes the Size of a Pizza and returns it
     * in String format.
     * @param size - Size of the Pizza
     * @return a String of the given Size
     */
    private String sizeToString(Size size) {
        if (size == Size.small) {
            return "small";
        }
        else if (size == Size.medium) {
            return "medium";
        }
        return "large";
    }
    
}