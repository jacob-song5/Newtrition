package com.example.newtrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.File;
import java.io.PrintWriter;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    //Action behind the calculate button. Extracts all data from fields.
    //Writes data to file 'profile_data' in internal storage in units of calories in format
    //calories
    //sugar
    //carbs
    //fat
    //protein
    public void save_profile(View view) {
        boolean male = false;
        double calorie_multiplier = 1.2;
        int calories, sugar, fat, carb, pro;

        EditText height_entry = this.findViewById(R.id.textEntryH);
        int height = Integer.parseInt(height_entry.getText().toString());

        EditText weight_entry = this.findViewById(R.id.textEntryW);
        int weight = Integer.parseInt(weight_entry.getText().toString());

        EditText age_entry = this.findViewById(R.id.ageEntry);
        int age = Integer.parseInt(age_entry.getText().toString());

        RadioGroup gender_group = this.findViewById(R.id.gender_group);
        int selected_gender = gender_group.getCheckedRadioButtonId();
        if (selected_gender == R.id.radioButtonMale)
            male = true;

        RadioGroup exercise_group = this.findViewById(R.id.exercise_group);
        int selected_exercise = exercise_group.getCheckedRadioButtonId();
        if (selected_exercise == R.id.radioButton_lightExercise)
            calorie_multiplier = 1.375;
        else if (selected_exercise == R.id.radioButton_moderateExercise)
            calorie_multiplier = 1.55;
        else if (selected_exercise == R.id.radioButton_heavyExercise)
            calorie_multiplier = 1.725;

        if (male)
        {
            calories = (int) ((66 + (6.3 * weight) + (12.9 * height) - (6.8 * age)) * calorie_multiplier);
            sugar = 150;
        }
        else
        {
            calories = (int) ((655 + (4.3 * weight) + (4.7 * height) - (4.7 * age)) * calorie_multiplier);
            sugar = 100;
        }

        carb = (int)(.65 * calories);
        fat = (int)(.35 * calories);
        pro = fat;

        try
        {
            File f = new File(this.getFilesDir(), "profile_data");
            PrintWriter out = new PrintWriter(f);
            out.println(calories);
            out.println(sugar);
            out.println(carb);
            out.println(fat);
            out.println(pro);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
