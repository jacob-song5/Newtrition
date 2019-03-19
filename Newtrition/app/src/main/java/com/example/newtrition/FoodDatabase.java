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
        FoodItem chicken_sandwich = new FoodItem("CHICKEN SANDWICH", 466.0, 39.06, 6.81, 20.93, 30.44);
        FoodItem cheese_pizza_slice = new FoodItem("CHEESE PIZZA SLICE", 278.0, 35.90, 4.34, 9.69, 11.66);
        FoodItem cheeseburger = new FoodItem("CHEESEBURGER", 343.0, 32.33, 6.65, 16.38, 17.13);
        FoodItem onion_rings = new FoodItem("ONION RINGS", 481.0, 50.99, 6.35, 29.52, 4.52);
        FoodItem breakfast_burrito = new FoodItem("BREAKFAST BURRITO", 302.0, 25.04, 2.80, 17.04, 12.10);
        FoodItem scrambled_eggs = new FoodItem("SCRAMBLED EGGS", 481.0, 2.00, 1.57, 15.53, 13.29);
        FoodItem soft_taco = new FoodItem("SOFT TACO", 210.0, 20.63, 1.60, 9.95, 9.44);
        FoodItem vanilla_ice_cream = new FoodItem("VANILLA ICE CREAM", 196.0, 31.63, 23.39, 5.83, 5.09);
        FoodItem chicken_nuggets = new FoodItem("CHICKEN NUGGETS", 222.0, 9.73, 0.05, 15.33, 11.19);
        FoodItem hotcakes = new FoodItem("HOTCAKES", 601.0, 101.84, 45.48, 17.81, 8.95);
        fdb.add(chicken_sandwich);
        fdb.add(cheese_pizza_slice);
        fdb.add(cheeseburger);
        fdb.add(onion_rings);
        fdb.add(breakfast_burrito);
        fdb.add(scrambled_eggs);
        fdb.add(soft_taco);
        fdb.add(vanilla_ice_cream);
        fdb.add(chicken_nuggets);
        fdb.add(hotcakes);
    }

    public FoodItem getFoodInfo(String f_name){
        for(int i=0; i<fdb.size(); i++){
            if(fdb.get(i).getName().equals(f_name)){
                return fdb.get(i);
            }
        }
        FoodItem not_found = new FoodItem("FOOD ITEM NOT FOUND", 0.0, 0.0, 0.0, 0.0, 0.0);
        return not_found;
    }

    //used to add foods to database
    //return -1 if failed to add (already exists), returns index of item in list if success
    //not needed anymore but keeping for utility
    public int addFoodInfo(String f_name, double f_cal, double f_carb, double f_sugar, double f_fats, double f_protein){
        int status = -1;
        int i = 0;
        for(i=0; i<fdb.size(); i++){
            if(fdb.get(i).getName().equals(f_name)){
                return status;
            }
        }
        fdb.add(new FoodItem(f_name, f_cal, f_carb, f_sugar, f_fats, f_protein));
        status = i;
        return status;
    }

    public int size_of_data()
    {
    	return fdb.size();
    }

    public FoodItem get_food(int x)
    {
    	return fdb.get(x);
    }

}
