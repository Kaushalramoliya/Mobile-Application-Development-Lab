package com.example.lab_3_name;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_3_name.R;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        Button submitButton = findViewById(R.id.submitButton);
        resultText = findViewById(R.id.resultText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String firstName = firstNameInput.getText().toString().trim();
                String lastName = lastNameInput.getText().toString().trim();

                String fullName = firstName + " " + lastName;

                resultText.setText("Hello, " + fullName + "!");
            }
        });
    }
}
