package api_models;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {

    private HashMap<String, ArrayList<String>> ingredientsMap;

    public Order(HashMap<String, ArrayList<String>> ingredientsMap) {
        this.ingredientsMap = ingredientsMap;
    }

    public Order() {

    }

    public HashMap<String, ArrayList<String>> getIngredientsMap() {
        return ingredientsMap;
    }

    public void setIngredientsMap(HashMap<String, ArrayList<String>> ingredientsMap) {
        this.ingredientsMap = ingredientsMap;
    }

}
