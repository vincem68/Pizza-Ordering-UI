package rupizza.rupizzeria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PizzasController{

	private MainMenuController mainController;

    @FXML
    private ListView<String> additionalTop;

    @FXML
    private TextField runningPrice;

    @FXML
    private ListView<String> selectedTop;

    @FXML
    private ComboBox<String> sizeDropDown;

    @FXML
    private ImageView deluxeImage;

    @FXML
    private ImageView pepperoniImage;

    @FXML
    private ImageView hawaiianImage;

    @FXML
    private TextField pizzaType;

    @FXML
    void addToOrder() {
        Pizza newPizza = PizzaMaker.createPizza(pizzaType.getText());
        if(newPizza == null){
            Alert nullPizza = new Alert(Alert.AlertType.INFORMATION);
            nullPizza.setTitle("Pizza Making Error.");
            nullPizza.setHeaderText(null);
            nullPizza.setContentText("Your specified pizza can not be made" +
                    "at this time.");
            nullPizza.showAndWait();
            return;
        }
        newPizza.setSize(sizeDropDown.getValue());
        ObservableList<String> stringOfToppings = selectedTop.getItems();
        ArrayList<Topping> replacementToppings = new ArrayList<>();
        for(Topping instance : Topping.values()){
            for(int i = 0; i < stringOfToppings.size(); i++) {
                if(instance.name().equals(stringOfToppings.get(i))){
                    replacementToppings.add(instance);
                }
            }
        }
        newPizza.setToppings(replacementToppings);
        mainController.currentOrder.getPizzas().add(newPizza);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza Added to Order");
        alert.setHeaderText(null);
        alert.setContentText("Your specified pizza has been added " +
                             "to the cart.");
        alert.showAndWait();
    }

    @FXML
    void addTopping(ActionEvent event) {
        ObservableList<String> currentToppings;
        currentToppings =  selectedTop.getItems();
        if(currentToppings.size() == Pizza.maxPizzaToppings){
            Alert maxToppingsError = new Alert(Alert.AlertType.ERROR);
            maxToppingsError.setTitle("Max Toppings Error");
            maxToppingsError.setHeaderText(null);
            maxToppingsError.setContentText("You can not add more than 7 " +
                                            "toppings to a Pizza.");
            maxToppingsError.showAndWait();
            return;
        }
        String addedTopping;
        ObservableList<String> newToppings;
        addedTopping = additionalTop.getSelectionModel().getSelectedItem();
        if(addedTopping == null){
            Alert noSelectionError = new Alert(Alert.AlertType.ERROR);
            noSelectionError.setTitle("No Selection Error");
            noSelectionError.setHeaderText(null);
            noSelectionError.setContentText("You must select a topping to " +
                                            "add.");
            noSelectionError.showAndWait();
            return;
        }
        currentToppings.add(addedTopping);
        selectedTop.setItems(currentToppings);
        newToppings = additionalTop.getItems();
        newToppings.removeAll(addedTopping);
        calculatePizzaPrice();
    }

    @FXML
    void displaySizes(ActionEvent event) {
        calculatePizzaPrice();
    }

    @FXML
    void removeTopping(ActionEvent event) {
        ObservableList<String> currentToppings;
        currentToppings =  selectedTop.getItems();
        ObservableList<String> newToppings;
        newToppings = additionalTop.getItems();
        String removedTopping;
        removedTopping = selectedTop.getSelectionModel().getSelectedItem();
        if(removedTopping == null){
            Alert noSelectionError = new Alert(Alert.AlertType.ERROR);
            noSelectionError.setTitle("No Selection Error");
            noSelectionError.setHeaderText(null);
            noSelectionError.setContentText("You must select a topping to " +
                    "remove.");
            noSelectionError.showAndWait();
            return;
        }
        if(removeToppingCheck(removedTopping)){
            currentToppings.removeAll(removedTopping);
            newToppings.add(removedTopping);
            calculatePizzaPrice();
        }
        else{
            pizzaDefaultToppingsError();
        }
    }

    private boolean removeToppingCheck(String topping){
        if(pizzaType.getText().equals("pepperoni")){
            if(topping.equals("pepperoni")){
                return false;
            }
        }
        else if(pizzaType.getText().equals("hawaiian")){
            if(topping.equals("pineapple") || topping.equals("ham")){
                return false;
            }
        }
        else if(pizzaType.getText().equals("deluxe")){
            if(topping.equals("bacon") || topping.equals("onion") ||
               topping.equals("chicken") || topping.equals("mushrooms") ||
               topping.equals("sausage")){
                return false;
            }
        }
       return true;
    }

    private void pizzaDefaultToppingsError(){
        if(pizzaType.getText().equals("pepperoni")){
            Alert defaultRemovalError = new Alert(Alert.AlertType.ERROR);
            defaultRemovalError.setTitle("Default Toppings Error");
            defaultRemovalError.setHeaderText(null);
            defaultRemovalError.setContentText("You can not remove the " +
                             "pepperoni topping from a Pepperoni Pizza.");
            defaultRemovalError.showAndWait();
        }
        else if(pizzaType.getText().equals("hawaiian")){
            Alert defaultRemovalError = new Alert(Alert.AlertType.ERROR);
            defaultRemovalError.setTitle("Default Toppings Error");
            defaultRemovalError.setHeaderText(null);
            defaultRemovalError.setContentText("You can not remove the " +
                    "pineapple and ham toppings from a  Hawaiian Pizza.");
            defaultRemovalError.showAndWait();
        }
        else{
            Alert defaultRemovalError = new Alert(Alert.AlertType.ERROR);
            defaultRemovalError.setTitle("Default Toppings Error");
            defaultRemovalError.setHeaderText(null);
            defaultRemovalError.setContentText("You can not remove the " +
                    "bacon, onion, chicken, mushrooms, or sausage toppings " +
                    "from a Deluxe Pizza.");
            defaultRemovalError.showAndWait();
        }
    }

    public void setMainController(MainMenuController controller){
        mainController = controller;
    }

    public void setDeluxePizzaScreen(){
        deluxeImage.setVisible(true);
        hawaiianImage.setVisible(false);
        pepperoniImage.setVisible(false);
        pizzaType.setText("deluxe");
        ObservableList<String> pizzaSizes =
                FXCollections.observableArrayList("small", "medium", "large");
        sizeDropDown.setItems(pizzaSizes);
        sizeDropDown.setValue("small");

        ObservableList<String> extraToppings =
                FXCollections.observableArrayList(
                        "pepperoni", "pineapple", "ham", "anchovies");
        ObservableList<String> defaultToppings =
                FXCollections.observableArrayList(
                        "bacon", "onion", "chicken", "mushrooms",
                        "sausage");
        additionalTop.setItems(extraToppings);
        selectedTop.setItems(defaultToppings);
        calculatePizzaPrice();
    }

    public void setHawaiianPizzaScreen(){
        hawaiianImage.setVisible(true);
        deluxeImage.setVisible(false);
        pepperoniImage.setVisible(false);
        pizzaType.setText("hawaiian");
        ObservableList<String> pizzaSizes =
                FXCollections.observableArrayList("small", "medium", "large");
        sizeDropDown.setItems(pizzaSizes);
        sizeDropDown.setValue("small");

        ObservableList<String> extraToppings =
                FXCollections.observableArrayList(
                        "pepperoni", "bacon", "onion", "chicken",
                        "mushrooms", "anchovies", "sausage");
        ObservableList<String> defaultToppings =
                FXCollections.observableArrayList(
                        "pineapple", "ham");
        additionalTop.setItems(extraToppings);
        selectedTop.setItems(defaultToppings);
        calculatePizzaPrice();
    }

    public void setPepperoniPizzaScreen(){
        pepperoniImage.setVisible(true);
        hawaiianImage.setVisible(false);
        deluxeImage.setVisible(false);
        pizzaType.setText("pepperoni");
        ObservableList<String> pizzaSizes =
                FXCollections.observableArrayList("small", "medium", "large");
        sizeDropDown.setItems(pizzaSizes);
        sizeDropDown.setValue("small");

        ObservableList<String> extraToppings =
                FXCollections.observableArrayList(
                        "pineapple", "ham", "bacon", "onion", "chicken",
                        "mushrooms", "anchovies", "sausage");
        ObservableList<String> defaultToppings =
                FXCollections.observableArrayList("pepperoni");
        additionalTop.setItems(extraToppings);
        selectedTop.setItems(defaultToppings);
        calculatePizzaPrice();
    }

    private void calculatePizzaPrice(){
        DecimalFormat df = new DecimalFormat("0.00");
        double currentPrice;
        String pizzaName;
        String currentSize;
        pizzaName = pizzaType.getText();
        currentSize = sizeDropDown.getSelectionModel().getSelectedItem();
        if(currentSize == null){
            currentSize = "small";
        }
        int selectedToppings = selectedTop.getItems().size();
        if(pizzaName.equals("pepperoni")){
            currentPrice = pepperoniPriceCalculation(currentSize,
                                                     selectedToppings);
        }
        else if(pizzaName.equals("hawaiian")){
            currentPrice = hawaiianPriceCalculation(currentSize,
                                                    selectedToppings);
        }
        else{
         currentPrice = deluxePriceCalculation(currentSize, selectedToppings);
        }
        runningPrice.setText(df.format(currentPrice));
    }

    private double pepperoniPriceCalculation(String size, int numToppings){
        double returnedPrice;
        if(size.equals("small")){
            returnedPrice = Pizza.pepperoniSmallPrice;
            if(numToppings > Pizza.numPepperoniToppings){
                returnedPrice = returnedPrice +
                                (numToppings-Pizza.numPepperoniToppings) *
                                Pizza.additionalToppings;
            }
        }
        else if(size.equals("medium")){
            returnedPrice = Pizza.pepperoniMediumPrice;
            if(numToppings > Pizza.numPepperoniToppings){
                returnedPrice = returnedPrice +
                                (numToppings-Pizza.numPepperoniToppings) *
                                Pizza.additionalToppings;
            }
        }
        else{
            returnedPrice = Pizza.pepperoniLargePrice;
            if(numToppings > Pizza.numPepperoniToppings){
                returnedPrice = returnedPrice +
                                (numToppings-Pizza.numPepperoniToppings) *
                                Pizza.additionalToppings;
            }
        }
        return returnedPrice;
    }
    private double hawaiianPriceCalculation(String size, int numToppings){
        double returnedPrice;
        if(size.equals("small")){
            returnedPrice = Pizza.hawaiianSmallPrice;
            if(numToppings > Pizza.numHawaiianToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numHawaiianToppings) *
                                Pizza.additionalToppings;
            }
        }
        else if(size.equals("medium")){
            returnedPrice = Pizza.hawaiianMediumPrice;
            if(numToppings > Pizza.numHawaiianToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numHawaiianToppings) *
                                Pizza.additionalToppings;
            }
        }
        else{
            returnedPrice = Pizza.hawaiianLargePrice;
            if(numToppings > Pizza.numHawaiianToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numHawaiianToppings) *
                                Pizza.additionalToppings;
            }
        }
        return returnedPrice;
    }
    private double deluxePriceCalculation(String size, int numToppings){
        double returnedPrice;
        if(size.equals("small")){
            returnedPrice = Pizza.deluxeSmallPrice;
            if(numToppings > Pizza.numDeluxeToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numDeluxeToppings) *
                                Pizza.additionalToppings;
            }
        }
        else if(size.equals("medium")){
            returnedPrice = Pizza.deluxeMediumPrice;
            if(numToppings > Pizza.numDeluxeToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numDeluxeToppings) *
                                Pizza.additionalToppings;
            }
        }
        else{
            returnedPrice = Pizza.deluxeLargePrice;
            if(numToppings > Pizza.numDeluxeToppings){
                returnedPrice = returnedPrice +
                        (numToppings-Pizza.numDeluxeToppings) *
                                Pizza.additionalToppings;
            }
        }
        return returnedPrice;
    }
}