package com.example.kahot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CustomerHomeActivity extends AppCompatActivity {

    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        // Initialize views
        textViewWelcome = findViewById(R.id.textViewWelcome);

        // Get the logged-in user's email from the FirebaseUser object
        String userEmail = getIntent().getStringExtra("userEmail");

        // Set a welcome message with the user's email
        String welcomeMessage = "Welcome, " + userEmail + "!";
        textViewWelcome.setText(welcomeMessage);
    }
}
