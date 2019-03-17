package com.example.newtrition;

public class FoodItem {
    String food_name = new String();
    double food_calories;
    double food_sugar;
    double food_carbs;
    double food_fats;
    double food_protein;

    public FoodItem(String name, double cal, double car, double sug, double fat, double pro){
        food_name = name;
        food_calories = cal;
        food_carbs = car;
        food_sugar = sug;
        food_fats = fat;
        food_protein = pro;
    }

    public String getName(){
        return food_name;
    }

    public double getCal(){
        return food_calories;
    }

    public double getCar(){
        return food_carbs;
    }

    public double getSug(){
        return food_sugar;
    }

    public double getFat(){
        return food_fats;
    }

    public double getPro(){
        return food_protein;
    }
}
