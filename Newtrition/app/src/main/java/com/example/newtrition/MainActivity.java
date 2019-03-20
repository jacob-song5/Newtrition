package com.example.newtrition;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.*;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button profile_button;
    private Button input_button;
    private Button suggestion_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_button = findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProfile();
                }
        });

        input_button = findViewById(R.id.input_button);
        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInput();
            }
        });

        suggestion_button = findViewById(R.id.suggestion_button);
        suggestion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuggest();
            }
        });

        //Code for starting the prompt to enter the user's weight and exercise level.
        //Uses weight_prompt.xml to get layout and AlertDialog builder to create the prompt.
        //Opens every time the app is opened, not every time the app goes to MainActivity
        LayoutInflater layout_inflater = LayoutInflater.from(this);
        View promptUserView = layout_inflater.inflate(R.layout.weight_prompt, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(promptUserView);
        final EditText weight_today = promptUserView.findViewById(R.id.daily_weight_prompt);
        builder.setTitle("What is your current weight and exercise level?");
        final RadioGroup r = promptUserView.findViewById(R.id.exercise_prompt_group);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save_to_file(weight_today.getText().toString(), r);
            }
        });

        builder.show();
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openInput() {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    public void openSuggest() {
        Intent intent = new Intent(this, SuggestActivity.class);
        startActivity(intent);
    }

    //Handler for when an item in the options menu is selected. Use if's to split up actions for each button
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.profile)
        {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //function used by AlertDialog "Ok" button for saving the data given
    //will not change data if there was no input or profile_data doesn't exist
    private void save_to_file(String weight, RadioGroup r)
    {
        if (weight != "") {
            int selected = r.getCheckedRadioButtonId();
            System.out.println("selected: "+selected);
            try {
                File f = new File(getFilesDir(), "profile_data");
                Scanner in = new Scanner(f);
                String height, age, gender, exercise_level, goal;
                height = in.nextLine();
                in.nextLine();
                age = in.nextLine();
                gender = in.nextLine();
                exercise_level = in.nextLine();
                goal = in.nextLine();
                in.close();

                PrintWriter out = new PrintWriter(f);
                out.println(height);
                out.println(weight);
                out.println(age);
                out.println(gender);

                if (selected != -1)
                    out.println(checked_button(selected));
                else
                    out.println(exercise_level);

                out.println(goal);
                out.close();
            } catch (NoSuchElementException n) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int checked_button(int selected)
    {
        if (selected == R.id.none_button)
            return 0;
        else if (selected == R.id.light_button)
            return 1;
        else if (selected == R.id.moderate_button)
            return 2;
        else if (selected == R.id.heavy_button)
            return 3;
        else
            return -1;
    }
}
