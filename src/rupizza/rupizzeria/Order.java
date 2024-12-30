package rupizza.rupizzeria;

import java.util.ArrayList;

public class Order {
	private String phoneNum;
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private boolean inProgress;

    public Order(String number, boolean inProgress){
        this.phoneNum = number;
        this.pizzas = new ArrayList<Pizza>();
        this.inProgress = inProgress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public boolean getInProgress(){
        return inProgress;
    }

    public void setInProgress(boolean status){
        this.inProgress = status;
    }

    public void setPizzas(ArrayList<Pizza> newPizzas){
        this.pizzas = newPizzas;
    }
}
