package com.example.registrationform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tvDisplay = findViewById(R.id.tvDisplay);

        // Retrieve data passed from MainActivity
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String gender = getIntent().getStringExtra("gender");
        String email = getIntent().getStringExtra("email");
        String contact = getIntent().getStringExtra("contact");

        // Display the data
        String displayText = "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Gender: " + gender + "\n" +
                "Email ID: " + email + "\n" +
                "Contact No: " + contact;
        tvDisplay.setText(displayText);
    }
}
