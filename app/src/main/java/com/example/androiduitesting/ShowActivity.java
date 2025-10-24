package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // connect the xml elements to java
        TextView cityNameText = findViewById(R.id.text_city_name);
        Button backButton = findViewById(R.id.button_back);

        // get the city name sent from mainactivity
        Intent receivedIntent = getIntent();
        String clickedCity = receivedIntent.getStringExtra("SELECTED_ACTIVITY");

        // show the city name on screen
        if (clickedCity != null) {
            cityNameText.setText(clickedCity);
        } else {
            cityNameText.setText("City not found");
        }

        // when back button is clicked, close
        backButton.setOnClickListener(view -> {
            finish(); // close current activity and go back
        });
    }
}