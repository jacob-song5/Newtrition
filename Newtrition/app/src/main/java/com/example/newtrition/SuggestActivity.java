package com.example.newtrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static com.example.newtrition.InputActivity.total_calories;
import static com.example.newtrition.InputActivity.total_carbs;
import static com.example.newtrition.InputActivity.total_lipids;
import static com.example.newtrition.InputActivity.total_protein;
import static com.example.newtrition.InputActivity.total_sugar;
import static com.example.newtrition.ProfileActivity.goal_weight;


public class SuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
    }

    public void suggest(View view)
    {
        // no prefix = total amount needed daily........
        // c = current consumption for the day........
        // n = needed to get to recomended daily.......
        // p = percentage of daily met already (for weighting purposes)
        int c_height, c_weight, c_age, c_gender, c_exercise;
        double p_calories, p_sugar, p_fat, p_carb, p_pro,c_calories, c_sugar, c_fat, c_carb, c_pro,
                n_calories, n_sugar, n_fat, n_carb, n_pro;

        String result1, result2, result3, ReceiveString;
        // retrieving the data for daily needs and current day consumption.
        String current[];
        current = new String[5];

        try
        {
            Context context = this;
            FileInputStream fis = context.openFileInput("profile_data");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            int place = 0;

            while ((line = bufferedReader.readLine()) != null) {
                current[place] = line;
                ++place;
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        //load saved health status
        c_height = Integer.parseInt(current[0]);
        c_weight = Integer.parseInt(current[1]);
        c_age = Integer.parseInt(current[2]);
        c_gender = Integer.parseInt(current[3]);
        c_exercise = Integer.parseInt(current[4]);

        System.out.println("height: "+c_height+", weight: "+c_weight+", age: "+c_age+", gender: "+c_gender+", exercise: "+c_exercise);

        //calculate suggested nutritional values and create model
        double cal_mult;
        int calc_cal, calc_sugar, calc_carb, calc_fat, calc_pro;
        if(c_exercise == 0){
            cal_mult = 1.0;
        }
        else if(c_exercise == 1){
            cal_mult = 1.375;
        }
        else if(c_exercise == 2){
            cal_mult = 1.55;
        }
        else{
            cal_mult = 1.725;
        }

        //male cal suggest
        if(c_gender == 1){
            calc_cal = (int)((66 + (6.3 * c_weight) + (12.9 * c_height) - (6.8 * c_age)) * cal_mult);
            calc_sugar = 150;
        }
        //female cal suggest
        else{
            calc_cal = (int)((655 + (4.3 * c_weight) + (4.7 * c_height) - (4.7 * c_age)) * cal_mult);
            calc_sugar = 100;
        }

        //calculate suggested nutritional values
        calc_carb = (int)(.65 * calc_cal);
        calc_fat = (int)(.35 * calc_cal);
        calc_pro = calc_fat;

        System.out.println("cal: "+calc_cal+", sugar: "+calc_sugar+", carb: "+calc_carb+", fat: "
                +calc_fat+", pro: "+calc_pro);

        int incase[];
        incase = new int[5];
        incase[0] = calc_cal;
        incase[1] = calc_sugar;
        incase[2] = calc_fat;
        incase[3] = calc_carb;
        incase[4] = calc_pro;

        //current
        c_calories = total_calories;
        c_sugar = total_sugar;
        c_fat = total_lipids;
        c_carb = total_carbs;
        c_pro = total_protein;

        //Figuring out how many of each category the user needs.
        n_calories = calc_cal - c_calories;
        n_sugar = calc_sugar - c_sugar;
        n_fat = calc_fat - c_fat;
        n_carb = calc_carb - c_carb;
        n_pro = calc_pro - c_pro;

        //Figuring out how many of each category the user needs.
        p_calories =  1 - (c_calories / calc_cal);
        p_sugar =  1 - (c_sugar / calc_sugar);
        p_fat = 1 - (c_fat / calc_fat);
        p_carb = 1 - (c_carb / calc_carb);
        p_pro = 1 - (c_pro / calc_pro);

        double holder[];
        holder = new double[5];
        holder[0] = p_calories;
        holder[1] = p_sugar;
        holder[2] = p_fat;
        holder[3] = p_carb;
        holder[4] = p_pro;

        double needed[];
        needed = new double[5];
        needed[0] = n_calories;
        needed[1] = n_sugar;
        needed[2] = n_fat;
        needed[3] = n_carb;
        needed[4] = n_pro;

        System.out.println("n_cal: "+n_calories+", n_sugar: "+n_sugar+", n_carb: "+n_carb+
                ", n_fat: "+n_fat+", n_pro: "+n_pro);

        for(int i = 0; i < 5; ++i){
            if(needed[i] < 0){
                needed[i] = 0;
            }
        }

        /*
        int places [];
        places = new int[5];

        for(int i = 0; i <= 4; ++i){
            int greater = 0;
            for(int x = 0; x <=4; ++ x){
                if(holder[i] > holder[x]){
                    ++greater;
                }
            }
            places[4-greater] = i;
        }
        */

        result1 = "No suggestion";
        result2 = "No suggestion";
        result3 = "No suggestion";

        //double score1 = 0, score2 = 0, score3 = 0, t_score = 0, multiplyer = 0;

        ArrayList<FoodItem> sug_foods = new ArrayList<>();
        FoodDatabase fd = new FoodDatabase();

        String food_name = "place holder";
        int data_size = fd.size_of_data();
        FoodItem current_food;

        //iterate through db to add all suggested foods
        for(int i=0; i<data_size; i++){
            current_food = fd.get_food(i);
            if((current_food.getCal() <= n_calories) && (current_food.getCar() <= n_carb) && (current_food.getSug() <= n_sugar)
                    && (current_food.getFat() <= n_fat) && (current_food.getPro() <= n_pro)){
                sug_foods.add(current_food);
            }
        }

        if(sug_foods.size() == 0){
            result1 = "No suggestion";
            result2 = "No suggestion";
            result3 = "No suggestion";
        }
        else if(sug_foods.size() == 1){
            result1 = sug_foods.get(0).food_name;
            result2 = "No suggestion";
            result3 = "No suggestion";
        }
        else if(sug_foods.size() == 2){
            Collections.shuffle(sug_foods);
            result1 = sug_foods.get(0).food_name;
            result2 = sug_foods.get(1).food_name;
            result3 = "No suggestion";
        }
        else{
            Collections.shuffle(sug_foods);
            result1 = sug_foods.get(0).food_name;
            result2 = sug_foods.get(1).food_name;
            result3 = sug_foods.get(2).food_name;
        }

        /*
        double nutrition[];
        nutrition = new double[5];

        for(int x = 0; x < data_size; ++x){
            t_score = 0;
            current_food = fd.get_food(x);
            food_name = current_food.getName();
            nutrition[0] = current_food.getCal();
            nutrition[1] = current_food.getSug();
            nutrition[2] = current_food.getFat();
            nutrition[3] = current_food.getCar();
            nutrition[4] = current_food.getPro();
            for(int i = 0; i < 5; ++i){

                if(places[i] == 0){multiplyer = 1;}
                else if(places[i] == 1){multiplyer = 1.2;}
                else if(places[i] == 2){multiplyer = 1.25;}
                else if(places[i] == 3){multiplyer = 1.3;}
                else if(places[i] == 4){multiplyer = 1.35;}


                double percentage_filled;
                // gives you a score based on the percentage of needed nutrition filled.
                percentage_filled = (nutrition[i] / needed[i])* 10;

                //if the nutritional value will go over your needs it drops it from consideration in the score.
                if(nutrition[i] > needed[i]){
                    percentage_filled = 0;
                }

                t_score += (percentage_filled * multiplyer);
            }
            if(t_score > score1){ result3 = result2; result2 = result1; result1 = food_name;}
            else if(t_score > score2){ result3 = result2; result2 = food_name; }
            else if(t_score > score3){result3 = food_name; }
        }
        */

        //Initializing the Text view boxes.
        TextView suggestion1 = (TextView) findViewById(R.id.suggestion1);
        TextView suggestion2 = (TextView) findViewById(R.id.suggestion2);
        TextView suggestion3 = (TextView) findViewById(R.id.suggestion3);

        // Displaying the food suggestions in the text boxes.

        suggestion1.setText("suggestion 1 - " + result1);
        suggestion2.setText("suggestion 2 - " + result2);
        suggestion3.setText("suggestion 3 - " + result3);

        System.out.println("GOAL WEIGHT: " + goal_weight);
    }
}