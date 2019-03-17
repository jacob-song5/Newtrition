package com.example.newtrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    //Allow user to search database to quickly add foods
    //Allow custom input of foods
    //Gather nutritional data for the day to build model

    //User's current daily nutrients to build the day's
    //personal model, but this excludes the other factor
    //in determining the personal model: exercise level
    public static double total_calories = 0;
    public static double total_carbs = 0;
    public static double total_sugar = 0;
    public static double total_lipids = 0;
    public static double total_protein = 0;

    //temporary fields to load inputs to
    double temp_calories = 0;
    double temp_carbs = 0;
    double temp_sugar = 0;
    double temp_lipids = 0;
    double temp_protein = 0;

    //temporary food name field for searches
    String temp_food_name = new String();

    //load database of nutritonal data
    FoodDatabase fd = new FoodDatabase();

    //live output of most recent nutritional data from model
    public static String total_data_text = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    //loads food data into page
    public void fetch_food(View view){
        EditText food_name_entry = this.findViewById(R.id.input_food_te);
        temp_food_name = food_name_entry.getText().toString().toUpperCase();

        FoodItem temp_food_item = fd.getFoodInfo(temp_food_name);
        if(temp_food_item.getName().equals("FOOD ITEM NOT FOUND")){
            food_name_entry.setText("Food item not found");
        }
        else{
            //Setting the data fields based on found food item data
            EditText calories_entry = this.findViewById(R.id.calorie_te);
            calories_entry.setText(Double.toString(temp_food_item.getCal()));
            EditText carbs_entry = this.findViewById(R.id.carb_te);
            carbs_entry.setText(Double.toString(temp_food_item.getCar()));
            EditText sugar_entry = this.findViewById(R.id.sugar_te);
            sugar_entry.setText(Double.toString(temp_food_item.getSug()));
            EditText fats_entry = this.findViewById(R.id.lipid_te);
            fats_entry.setText(Double.toString(temp_food_item.getFat()));
            EditText protein_entry = this.findViewById(R.id.protein_te);
            protein_entry.setText(Double.toString(temp_food_item.getPro()));
        }
    }

    //adds food item nutritional info to personal data
    public void add_food(View view){
        EditText calories_entry = this.findViewById(R.id.calorie_te);
        temp_calories = Double.parseDouble(calories_entry.getText().toString());

        EditText carbs_entry = this.findViewById(R.id.carb_te);
        temp_carbs = Double.parseDouble(carbs_entry.getText().toString());

        EditText sugar_entry = this.findViewById(R.id.sugar_te);
        temp_sugar = Double.parseDouble(sugar_entry.getText().toString());

        EditText fats_entry = this.findViewById(R.id.lipid_te);
        temp_lipids = Double.parseDouble(fats_entry.getText().toString());

        EditText protein_entry = this.findViewById(R.id.protein_te);
        temp_protein = Double.parseDouble(protein_entry.getText().toString());

        total_calories += temp_calories;
        total_carbs += temp_carbs;
        total_sugar += temp_sugar;
        total_lipids += temp_lipids;
        total_protein += temp_protein;

        //Use this string to display and update live nutrition values
        //You need to .setText(total_data_text) the textView or whatever we use
        //here to where ever you want to display it to keep it updated
        total_data_text = "Current cal: " + total_calories + ", carb:" + total_carbs +
                ", sugar: " + total_sugar + ", lipids: " + total_lipids + ", protein: " + total_protein;
        System.out.println(total_data_text);

        System.out.println("TOTAL CAL: " + total_calories);
        System.out.println("TOTAL CARBS: " + total_carbs);
        System.out.println("TOTAL SUGAR: " + total_sugar);
        System.out.println("TOTAL FATS: " + total_lipids);
        System.out.println("TOTAL PROTEIN: " + total_protein);
    }

}
