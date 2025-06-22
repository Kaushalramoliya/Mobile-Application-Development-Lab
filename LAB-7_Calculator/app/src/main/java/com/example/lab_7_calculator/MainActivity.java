package com.example.lab_7_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String expression = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText2);

        // Button Initialization
        Button btn0 = findViewById(R.id.id0);
        Button btn1 = findViewById(R.id.id1);
        Button btn2 = findViewById(R.id.id2);
        Button btn3 = findViewById(R.id.id3);
        Button btn4 = findViewById(R.id.id4);
        Button btn5 = findViewById(R.id.id5);
        Button btn6 = findViewById(R.id.id6);
        Button btn7 = findViewById(R.id.id7);
        Button btn8 = findViewById(R.id.id8);
        Button btn9 = findViewById(R.id.id9);
        Button btnAdd = findViewById(R.id.addid);
        Button btnSub = findViewById(R.id.subid);
        Button btnMul = findViewById(R.id.mulid);
        Button btnDiv = findViewById(R.id.slesh);
        Button btnMod = findViewById(R.id.percentageid);
        Button btnEquals = findViewById(R.id.eqlid);
        Button btnClear = findViewById(R.id.acid);
        Button btnDelete = findViewById(R.id.delid);
        Button btnPoint = findViewById(R.id.pntid);

        // Number Button Listeners
        View.OnClickListener numberClickListener = v -> {
            Button b = (Button) v;
            expression += b.getText().toString();
            editText.setText(expression);
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        // Operator Button Listeners
        btnAdd.setOnClickListener(v -> onOperatorClick("+"));
        btnSub.setOnClickListener(v -> onOperatorClick("-"));
        btnMul.setOnClickListener(v -> onOperatorClick("*"));
        btnDiv.setOnClickListener(v -> onOperatorClick("/"));
        btnMod.setOnClickListener(v -> onOperatorClick("%"));

        // Equals Button Listener
        btnEquals.setOnClickListener(v -> onEqualClick());

        // Clear Button Listener
        btnClear.setOnClickListener(v -> clearAll());

        // Delete Button Listener
        btnDelete.setOnClickListener(v -> deleteLastChar());

        // Decimal Point Button Listener
        btnPoint.setOnClickListener(v -> appendDecimal());
    }

    private void onOperatorClick(String op) {
        if (!expression.isEmpty() && !"+-*/%".contains(String.valueOf(expression.charAt(expression.length() - 1)))) {
            expression += " " + op + " ";
            editText.setText(expression);
        }
    }

    @SuppressLint("SetTextI18n")
    private void onEqualClick() {
        try {
            double result = evaluateExpression(expression);
            expression = String.valueOf(result);
            editText.setText(expression);
        } catch (Exception e) {
            editText.setText("Error");
            clearAll();
        }
    }

    private double evaluateExpression(String expr) throws Exception {
        // Use a stack-based approach to evaluate the expression
        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    sb.append(expr.charAt(i++));
                }
                i--;
                values.push(Double.parseDouble(sb.toString()));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
                while (!ops.empty() && precedence(c) <= precedence(ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return -1;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                }
                throw new UnsupportedOperationException("Cannot divide by zero");
            case '%':
                return a % b;
        }
        return 0;
    }

    private void appendDecimal() {
        if (expression.isEmpty() || "+-*/%".contains(String.valueOf(expression.charAt(expression.length() - 1)))) {
            expression += "0.";
        } else if (!expression.contains(".")) {
            expression += ".";
        }
        editText.setText(expression);
    }

    private void clearAll() {
        expression = "";
        editText.setText("");
    }

    private void deleteLastChar() {
        if (!expression.isEmpty()) {
            expression = expression.substring(0, expression.length() - 1);
            if (expression.endsWith(" ")) {
                expression = expression.substring(0, expression.length() - 1);
            }
            editText.setText(expression);
        }
    }
}
