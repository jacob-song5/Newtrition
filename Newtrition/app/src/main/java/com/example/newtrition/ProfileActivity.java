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
    //Writes data to file 'profile_data' in internal storage in format:
    //height in inches
    //weight in inches
    //age in years
    //gender as female(0) or male(1)
    //level of exercise: none(0), light(1), moderate(2), heavy(3)
    public void save_profile(View view) {
        int male = 1, exercise_level = 0;
        String height, weight, age;

        EditText height_entry = this.findViewById(R.id.textEntryH);
        height = height_entry.getText().toString();

        EditText weight_entry = this.findViewById(R.id.textEntryW);
        weight = weight_entry.getText().toString();

        EditText age_entry = this.findViewById(R.id.ageEntry);
        age = age_entry.getText().toString();

        RadioGroup gender_group = this.findViewById(R.id.gender_group);
        int selected_gender = gender_group.getCheckedRadioButtonId();
        if (selected_gender == R.id.radioButtonFemale)
            male = 0;

        RadioGroup exercise_group = this.findViewById(R.id.exercise_group);
        int selected_exercise = exercise_group.getCheckedRadioButtonId();
        if (selected_exercise == R.id.radioButton_lightExercise)
            exercise_level = 1;
        else if (selected_exercise == R.id.radioButton_moderateExercise)
            exercise_level = 2;
        else if (selected_exercise == R.id.radioButton_heavyExercise)
            exercise_level = 3;

        try
        {
            File f = new File(this.getFilesDir(), "profile_data");
            PrintWriter out = new PrintWriter(f);
            out.println(height);
            out.println(weight);
            out.println(age);
            out.println(male);
            out.println(exercise_level);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
