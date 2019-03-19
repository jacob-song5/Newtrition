package com.example.newtrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;



public class SuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
    }



    public void suggest(View view)
    {
        // no prefix = total amount needed daily........ c = current consumption for the day........  n = needed to get to recomended daily....... p = percentage of daily met already (for weighting purposes)
        int calories, sugar, fat, carb, pro, c_calories, c_sugar, c_fat, c_carb, c_pro, n_calories, n_sugar, n_fat, n_carb, n_pro, p_pro, f_calories, f_sugar, f_fat, f_carb, f_pro;
        double p_calories, p_sugar, p_fat, p_carb;
        int count = 0;


        c_calories = 1000;
        c_sugar = 150;
        c_fat = 100;
        c_carb = 315;
        c_pro = 45;

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

        calories = Integer.parseInt(current[0]);
        sugar = Integer.parseInt(current[1]);
        fat = Integer.parseInt(current[2]);
        carb = Integer.parseInt(current[3]);
        pro = Integer.parseInt(current[4]);

        //Figuring out how many of each category the user needs.
        n_calories = calories - c_calories;
        n_sugar = sugar - c_sugar;
        n_fat = fat - c_fat;
        n_carb = carb - c_carb;
        n_pro = pro - c_pro;

        //Figuring out how many of each category the user needs.
        p_calories =  100 - (c_calories / calories);
        p_sugar =  100 - (c_sugar / sugar);
        p_fat = 100 - (c_fat / fat);
        p_carb = 100 - (c_carb / carb);
        p_pro = 100 - (c_pro / pro);

        double holder[];
        holder = new double[5];
        holder[0] = p_calories;
        holder[1] = p_sugar;
        holder[2] = p_fat;
        holder[3] = p_carb;
        holder[4] = p_pro;

        int needed[];
        needed = new int[5];
        needed[0] = n_calories;
        needed[1] = n_sugar;
        needed[2] = n_fat;
        needed[3] = n_carb;
        needed[4] = n_pro;


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

        result1 = "test";
        result2 = "what? how?";
        result3 = "boop";

        double score1 = 0, score2 = 0, score3 = 0, t_score = 0, multiplyer = 0;

        try
        {
            Context context = this;
            FileInputStream fis = context.openFileInput("foods");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;

            String food_name = "place holder";
            int nutrition[];
            nutrition = new int[5];

            while ((line = bufferedReader.readLine()) != null) {
                /* import data into needed variables */
                for(int i = 0; i < 5; ++i){

                    if(places[i] == 0){multiplyer = 1;}
                    if(places[i] == 1){multiplyer = 1.2;}
                    if(places[i] == 2){multiplyer = 1.3;}
                    if(places[i] == 3){multiplyer = 1.4;}
                    if(places[i] == 4){multiplyer = 1.5;}

                    if(nutrition[i] > needed[i]){
                        multiplyer = .9;
                    }
                    double percentage_filled ;
                    percentage_filled = (nutrition[i] / needed[i])*10;

                    t_score += percentage_filled*multiplyer;
                }
                if(t_score > score1){ result1 = food_name; }
                else if(t_score > score2){ result2 = food_name; }
                else if(t_score > score3){ result3 = food_name; }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }


        //Initializing the Text view boxes.
        TextView suggestion1 = (TextView) findViewById(R.id.suggestion1);
        TextView suggestion2 = (TextView) findViewById(R.id.suggestion2);
        TextView suggestion3 = (TextView) findViewById(R.id.suggestion3);

        // Displaying the food suggestions in the text boxes.

        suggestion1.setText("suggestion 1 - " + result1);
        suggestion2.setText("suggestion 2 - " + result2);
        suggestion3.setText("suggestion 3 - " + result3);
    }
}


