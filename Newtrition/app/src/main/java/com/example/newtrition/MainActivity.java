package com.example.newtrition;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private Button profile_button;
    private Button input_button;
    private Button suggestion_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_button = (Button) findViewById(R.id.profile_button);
        profile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openProfile();
                }
        });

        input_button = (Button) findViewById(R.id.input_button);
        input_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInput();
            }
        });

        suggestion_button = (Button) findViewById(R.id.suggestion_button);
        suggestion_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuggest();
            }
        });
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
}
