package rupizza.rupizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainMenuController {

	 //initialize storeOrders array list
    StoreOrders storeOrders = new StoreOrders();

    //initialize currentOrder
    Order currentOrder = new Order(null, false);

    //for loading pizza customization window
    FXMLLoader pizzasLoader = new FXMLLoader(getClass().getResource(
                        "CustomizePizzas.fxml"));
    Scene pizzasScene = new Scene(pizzasLoader.load(), 640, 700);
    PizzasController pizzasController = pizzasLoader.getController();

    //for loading current order window
    FXMLLoader currentOrderLoader = new FXMLLoader(getClass().getResource(
                              "CurrentOrder.fxml"));
    Scene currentOrderScene = new Scene(currentOrderLoader.load(), 640, 700);
    CurrentOrderController currentOrderController =
                           currentOrderLoader.getController();

    //for loading store orders window
    FXMLLoader storeOrdersLoader = new FXMLLoader(getClass().getResource(
                             "StoreOrders.fxml"));
    Scene storeOrdersScene = new Scene(storeOrdersLoader.load(), 640, 700);
    StoreOrdersController storeOrdersController =
            storeOrdersLoader.getController();


    //stages for the three different windows
    private Stage pizzaCustomizationStage;

    private Stage currentOrderStage;

    private Stage storeOrdersStage;

    private static final int phoneNumLength = 10;

    @FXML
    private TextField custPhone;

    public MainMenuController() throws IOException {
    }

   @FXML
   void checkCurrentOrder(ActionEvent event) {
       if(currentOrderStage != null &&
          currentOrderStage.isShowing()) {
          currentOrderStage.close();
       }
       if(!phoneNumIsValid(custPhone.getText())) {
           return;
       }
       if(!checkUniqueNumber(custPhone.getText())){
           return;
       }
       if(!currentOrder.getInProgress()){
           Alert noOrder = new Alert(Alert.AlertType.ERROR);
           noOrder.setTitle("Current Order");
           noOrder.setHeaderText(null);
           noOrder.setContentText("There is no order in progress.");
           noOrder.showAndWait();
           return;
       }
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Current Order");
       alert.setHeaderText(null);
       alert.setContentText("Checking Current Order...");
       alert.showAndWait();
       currentOrderController.setMainController(this);
       try {
           currentOrderStage = openCurrentOrderWindow();
       }
       catch (IOException e) {
           return;
       }
   }

    @FXML
    void checkStoreOrders(ActionEvent event) {
        if(storeOrdersStage != null &&
                storeOrdersStage.isShowing()) {
            storeOrdersStage.close();
        }
        if(storeOrders.getOrders().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Orders");
            alert.setHeaderText(null);
            alert.setContentText("There are currently no orders that have" +
                    " been placed.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("All Store Orders");
        alert.setHeaderText(null);
        alert.setContentText("Checking Store Orders...");
        alert.showAndWait();
        storeOrdersController.setMainController(this);
        try {
            storeOrdersStage = openStoreOrdersWindow();
        }
        catch (IOException e) {
            return;
        }
    }

    @FXML
    void orderDeluxe(ActionEvent event) throws IOException {
        if(pizzaCustomizationStage != null &&
           pizzaCustomizationStage.isShowing()) {
           pizzaCustomizationStage.close();
        }
        if (!phoneNumIsValid(custPhone.getText())) {
            return;
        }
        if(!checkUniqueNumber(custPhone.getText())){
            return;
        }
        custPhone.setEditable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("Adding Deluxe Pizza to Your Order...");
        alert.setContentText("You will not be able to input a new order " +
                             "until the current order associated with " +
                             "the given phone number is placed.");
        alert.showAndWait();
        try {
            pizzaCustomizationStage = openDeluxePizzaWindow();
        }
        catch (IOException e) {
            return;
        }
        if(currentOrder.getPhoneNum() == null){
            Order deluxeOrder = new Order(custPhone.getText(), true);
            currentOrder = deluxeOrder;
        }
        pizzasController.setMainController(this);

    }

    @FXML
    void orderHawaiian(ActionEvent event) {
        if(pizzaCustomizationStage != null &&
           pizzaCustomizationStage.isShowing()) {
           pizzaCustomizationStage.close();
        }
        if(!phoneNumIsValid(custPhone.getText())){
            return;
        }
        if(!checkUniqueNumber(custPhone.getText())){
            return;
        }
        custPhone.setEditable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("Adding Hawaiian Pizza to Your Order...");
        alert.setContentText("You will not be able to input a new order " +
                             "until the current order associated with " +
                             "the given phone number is placed.");
        alert.showAndWait();
        try{
            pizzaCustomizationStage = openHawaiianPizzaWindow();
        }
        catch (IOException e){
            return;
        }
        if(currentOrder.getPhoneNum() == null){
            Order hawaiianOrder = new Order(custPhone.getText(), true);
            currentOrder = hawaiianOrder;
        }
        pizzasController.setMainController(this);

    }

    @FXML
    void orderPepperoni(ActionEvent event) {
        if(pizzaCustomizationStage != null &&
           pizzaCustomizationStage.isShowing()) {
           pizzaCustomizationStage.close();
        }
        if(!phoneNumIsValid(custPhone.getText())){
            return;
        }
        if(!checkUniqueNumber(custPhone.getText())){
            return;
        }
        custPhone.setEditable(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Status");
        alert.setHeaderText("Adding Pepperoni Pizza to Your Order...");
        alert.setContentText("You will not be able to input a new order " +
                             "until the current order associated with " +
                             "the given phone number is placed.");
        alert.showAndWait();
        try{
            pizzaCustomizationStage = openPepperoniPizzaWindow();
        }
        catch (IOException e){
            return;
        }
        if(currentOrder.getPhoneNum() == null){
            Order pepperoniOrder = new Order(custPhone.getText(), true);
            currentOrder = pepperoniOrder;
        }
        pizzasController.setMainController(this);
    }
    private boolean phoneNumIsValid(String phone){
        if(phone.isEmpty()){
            Alert missingPhoneNum = new Alert(Alert.AlertType.ERROR);
            missingPhoneNum.setTitle("Phone Number Error");
            missingPhoneNum.setHeaderText(null);
            missingPhoneNum.setContentText("Please enter your 10 digit " +
                    "phone number.");
            missingPhoneNum.showAndWait();
            return false;
        }
        if(phone.length() != phoneNumLength){
            Alert lengthError = new Alert(Alert.AlertType.ERROR);
            lengthError.setTitle("Phone Number Error");
            lengthError.setHeaderText(null);
            lengthError.setContentText("Your phone number must be " +
                    "exactly 10 digits.");
            lengthError.showAndWait();
            return false;
        }
        try{
            Long.parseLong(phone);
        }
        catch (NumberFormatException e){
            Alert parseError = new Alert(Alert.AlertType.ERROR);
            parseError.setTitle("Phone Number Error");
            parseError.setHeaderText(null);
            parseError.setContentText("The phone number you entered is " +
                    "not fully numeric.");
            parseError.showAndWait();
            return false;
        }
        return true;
    }

    private Stage openDeluxePizzaWindow() throws IOException {
        pizzasController.setDeluxePizzaScreen();
        Stage stage = new Stage();
        stage.setTitle("RUPizzeria.com/OrderDeluxePizza");
        stage.setScene(pizzasScene);
        stage.show();
        return stage;
    }
    private Stage openHawaiianPizzaWindow() throws IOException{
        pizzasController.setHawaiianPizzaScreen();
        Stage stage = new Stage();
        stage.setTitle("RUPizzeria.com/OrderHawaiianPizza");
        stage.setScene(pizzasScene);
        stage.show();
        return stage;
    }

    private Stage openPepperoniPizzaWindow() throws IOException{
        pizzasController.setPepperoniPizzaScreen();
        Stage stage = new Stage();
        stage.setTitle("RUPizzeria.com/OrderPepperoniPizza");
        stage.setScene(pizzasScene);
        stage.show();
        return stage;
    }

    private Stage openCurrentOrderWindow() throws IOException{
        currentOrderController.setCurrentOrderScreen();
        Stage stage = new Stage();
        stage.setTitle("RUPizzeria.com/CurrentOrder");
        stage.setScene(currentOrderScene);
        stage.show();
        return stage;
    }

    private Stage openStoreOrdersWindow() throws IOException{
        storeOrdersController.setStoreOrderScreen();
        Stage stage = new Stage();
        stage.setTitle("RUPizzeria.com/StoreOrders");
        stage.setScene(storeOrdersScene);
        stage.show();
        return stage;
    }

    public void changeCustomerPhone() {
        custPhone.setEditable(true);
        custPhone.setText("");
    }

    public void closeCurrentOrderWindow(){
        currentOrderStage.close();
    }

    private boolean checkUniqueNumber(String newNum){
        for(int i = 0; i < storeOrders.getOrders().size(); i++){
            if(newNum.equals(storeOrders.getOrders().get(i).getPhoneNum())){
                Alert nonUniqueNum = new Alert(Alert.AlertType.ERROR);
                nonUniqueNum.setTitle("Phone Number Error");
                nonUniqueNum.setHeaderText(null);
                nonUniqueNum.setContentText("There is already an order" +
                        " associated with this number. Please use a new" +
                        " number.");
                nonUniqueNum.showAndWait();
                return false;
            }
        }
        return true;
    }

    public String completeOrder(Pizza pizza) {
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

    private String sizeToString(Size size) {
        if (size == Size.small) {
            return "small";
        }
        else if (size == Size.medium) {
            return "medium";
        }
        return "large";
    }

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
}