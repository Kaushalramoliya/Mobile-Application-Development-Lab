package com.example.lab_4_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText number1Input;
    private EditText number2Input;
    private EditText resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1Input = findViewById(R.id.number1Input);
        number2Input = findViewById(R.id.number2Input);
        resultOutput = findViewById(R.id.resultOutput);

        Button addButton = findViewById(R.id.addButton);
        Button subButton = findViewById(R.id.subButton);
        Button mulButton = findViewById(R.id.mulButton);
        Button divButton = findViewById(R.id.divButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
    }

    private void calculate(String operator) {
        String num1 = number1Input.getText().toString();
        String num2 = number2Input.getText().toString();

        if (!num1.isEmpty() && !num2.isEmpty()) {
            double number1 = Double.parseDouble(num1);
            double number2 = Double.parseDouble(num2);
            double result = 0;

            switch (operator) {
                case "+":
                    result = number1 + number2;
                    break;
                case "-":
                    result = number1 - number2;
                    break;
                case "*":
                    result = number1 * number2;
                    break;
                case "/":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        resultOutput.setText("Cannot divide by zero");
                        return;
                    }
                    break;
            }
            resultOutput.setText(String.valueOf(result));
        } else {
            resultOutput.setText("Please enter both numbers");
        }
    }
}
