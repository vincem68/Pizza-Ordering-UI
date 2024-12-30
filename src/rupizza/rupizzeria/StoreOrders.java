package rupizza.rupizzeria;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> orders = new ArrayList<Order>();

    public ArrayList<Order> getOrders() {
        return orders;
    }

    //NEED TO CHECK THIS METHOD
    public void export(File exportFile) throws IOException {
        PrintWriter outputStream = new PrintWriter(exportFile);
        outputStream.print("ALL STORE ORDERS:");
        for(Order instance : this.orders){
            outputStream.println("New Customer:");
            outputStream.print(instance.getPhoneNum());
            for(int i = 0; i < instance.getPizzas().size(); i++){
                Pizza pizzaInstance = instance.getPizzas().get(i);
                if(pizzaInstance instanceof Pepperoni){
                    outputStream.println(pizzaInstance.size);
                    outputStream.print("pepperoni pizza topped with:");
                    for(int j = 0; j < pizzaInstance.toppings.size(); j++){
                        outputStream.println(pizzaInstance.toppings.get(j));
                    }
                }
                else if(pizzaInstance instanceof Hawaiian){
                    outputStream.println(pizzaInstance.size);
                    outputStream.println("hawaiian pizza topped with:");
                    for(int j = 0; j < pizzaInstance.toppings.size(); j++){
                        outputStream.println(pizzaInstance.toppings.get(j));
                    }
                }
                else{
                    outputStream.println(pizzaInstance.size);
                    outputStream.println("deluxe pizza topped with:");
                    for(int j = 0; j < pizzaInstance.toppings.size(); j++){
                        outputStream.println(pizzaInstance.toppings.get(j));
                    }
                }
            }
        }
        outputStream.println("NO MORE ORDERS.");
    }

    //THIS CODE IS FOR HANDLING EXPORT FROM THE CONTROLLER SIDE
    //PLACING IT HERE FOR NOW SINCE IT IS RELATED:
    
    //void exportFile(ActionEvent event) {
    //    FileChooser chooser = new FileChooser();
    //    chooser.setTitle("Open Target File for the Export");
    //    chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
    //            new ExtensionFilter("All Files", "*.*"));
    //    Stage stage = new Stage();
    //    File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
    //    //write code to write to the file.
    //}
}
