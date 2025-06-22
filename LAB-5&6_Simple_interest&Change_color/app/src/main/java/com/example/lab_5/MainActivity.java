package com.example.lab_5;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText amountEditText, interestEditText, yearsEditText;
    private TextView resultTextView;
    private Button calculateButton;
    private int clickCount = 0;

    private final int[] backgroundColors = {Color.parseColor("#552619"), Color.parseColor("#8AB6F9"), Color.parseColor("#EDCD44")};
    private final int[] textSizes = {18, 22, 26};
    private final int[] textColors = {Color.parseColor("#FBEAE7"), Color.parseColor("#00246B"), Color.parseColor("#404041")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.editTextText3);
        interestEditText = findViewById(R.id.editTextText4);
        yearsEditText = findViewById(R.id.editTextText5);
        calculateButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.textView2);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSimpleInterest();
                changeButtonAppearance();  // Change button appearance after each click
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculateSimpleInterest() {
        // Get input values as strings and convert to double
        String amountStr = amountEditText.getText().toString();
        String interestStr = interestEditText.getText().toString();
        String yearsStr = yearsEditText.getText().toString();

        if (amountStr.isEmpty() || interestStr.isEmpty() || yearsStr.isEmpty()) {
            resultTextView.setText("Please enter all values.");
            return;
        }

        double principal = Double.parseDouble(amountStr);
        double rate = Double.parseDouble(interestStr);
        double time = Double.parseDouble(yearsStr);

        double simpleInterest = (principal * rate * time) / 100;
        double totalAmount = principal + simpleInterest;

        resultTextView.setText("Simple Interest: " + simpleInterest + "\nTotal Amount: " + totalAmount);
    }

    private void changeButtonAppearance() {
        // Cycle through different colors and sizes
        int index = clickCount % backgroundColors.length;

        // Change button background color, text size, and text color
        calculateButton.setBackgroundColor(backgroundColors[index]);
        calculateButton.setTextSize(textSizes[index]);
        calculateButton.setTextColor(textColors[index]);

        clickCount++;
    }
}
