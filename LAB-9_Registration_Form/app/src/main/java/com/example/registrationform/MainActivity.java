package com.example.registrationform;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etGender, etEmail, etContact;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing the EditText and Button views
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etGender = findViewById(R.id.etGender);
        etEmail = findViewById(R.id.etEmail);
        etContact = findViewById(R.id.etContact);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Setting the click listener for the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetching values from the form
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String gender = etGender.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String contact = etContact.getText().toString().trim();

                // Validating the form inputs
                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(MainActivity.this, "Please enter your first name.", Toast.LENGTH_SHORT).show();
                } else if (!firstName.matches("[a-zA-Z]+")) {
                    Toast.makeText(MainActivity.this, "First name should contain only letters.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lastName)) {
                    Toast.makeText(MainActivity.this, "Please enter your last name.", Toast.LENGTH_SHORT).show();
                } else if (!lastName.matches("[a-zA-Z]+")) {
                    Toast.makeText(MainActivity.this, "Last name should contain only letters.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(gender)) {
                    Toast.makeText(MainActivity.this, "Please enter your gender.", Toast.LENGTH_SHORT).show();
                } else if (!(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))) {
                    Toast.makeText(MainActivity.this, "Gender must be 'M' or 'F'.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(MainActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(contact)) {
                    Toast.makeText(MainActivity.this, "Please enter your contact number.", Toast.LENGTH_SHORT).show();
                } else if (!contact.matches("\\d+")) {
                    Toast.makeText(MainActivity.this, "Contact number should contain only numbers.", Toast.LENGTH_SHORT).show();
                } else if (contact.length() != 10) {
                    Toast.makeText(MainActivity.this, "Contact number should be exactly 10 digits.", Toast.LENGTH_SHORT).show();
                } else {
                    // Start DisplayActivity and pass the form data
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("gender", gender);
                    intent.putExtra("email", email);
                    intent.putExtra("contact", contact);
                    startActivity(intent);
                }
            }
        });
    }
}
