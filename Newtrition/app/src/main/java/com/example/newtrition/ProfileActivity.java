package com.example.newtrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    //Action behind the calculate button. Extracts all data from fields
    public void save_profile(View view) {
        boolean male = false;
        double calorie_multiplier = 1.2;

        EditText height_entry = this.findViewById(R.id.textEntryH);
        int height = Integer.parseInt(height_entry.getText().toString());

        EditText weight_entry = this.findViewById(R.id.textEntryW);
        int weight = Integer.parseInt(weight_entry.getText().toString());

        EditText age_entry = this.findViewById(R.id.ageEntry);
        int age = Integer.parseInt(age_entry.getText().toString());

        RadioGroup gender_group = (RadioGroup) this.findViewById(R.id.gender_group);
        int selected_gender = gender_group.getCheckedRadioButtonId();
        if (selected_gender == R.id.radioButtonMale)
            male = true;

        RadioGroup exercise_group = (RadioGroup) this.findViewById(R.id.exercise_group);
        int selected_exercise = exercise_group.getCheckedRadioButtonId();
        if (selected_exercise == R.id.radioButton_lightExercise)
            calorie_multiplier = 1.375;
        else if (selected_exercise == R.id.radioButton_moderateExercise)
            calorie_multiplier = 1.55;
        else if (selected_exercise == R.id.radioButton_heavyExercise)
            calorie_multiplier = 1.725;

    }
}
