package com.example.newtrition;

import java.util.ArrayList;

public class FoodDatabase {
    //list of food items
    ArrayList<FoodItem> fdb = new ArrayList<FoodItem>();

    //instantiate list of foods
    //normally would have a database API
    //THEN parse database to extract data info
    //manually input foods for demo purposes
    public FoodDatabase(){
        FoodItem chicken_sandwich = new FoodItem("Chicken Sandwich", 466.0, 39.06, 6.81, 20.93, 30.44);
        FoodItem cheese_pizza_slice = new FoodItem("Cheese Pizza Slice", 278.0, 35.90, 4.34, 9.69, 11.66);
        FoodItem cheeseburger = new FoodItem("Cheeseburger", 343.0, 32.33, 6.65, 16.38, 17.13);
        FoodItem onion_rings = new FoodItem("Onion Rings", 481.0, 50.99, 6.35, 29.52, 4.52);
        fdb.add(chicken_sandwich);
        fdb.add(cheese_pizza_slice);
        fdb.add(cheeseburger);
        fdb.add(onion_rings);
    }

    public FoodItem getFoodInfo(String f_name){
        for(int i=0; i<fdb.size(); i++){
            if(fdb.get(i).getName().equals(f_name)){
                return fdb.get(i);
            }
        }
        FoodItem not_found = new FoodItem("Food item not found", 0.0, 0.0, 0.0, 0.0, 0.0);
        return not_found;
    }

}
