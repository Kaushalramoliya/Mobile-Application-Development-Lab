package com.example.lab_8_intent_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button send_btn = findViewById(R.id.button);
        EditText send_txt = findViewById(R.id.editTextText);

        send_btn.setOnClickListener(view -> {
            String str = send_txt.getText().toString();
            Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
            intent.putExtra("message_key", str);
            startActivity(intent);
        });

        EditText link_text = findViewById(R.id.link_edit_text);
        Button search_btn = findViewById(R.id.search_button);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = link_text.getText().toString();
                Intent intentserch = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intentserch);
            }
        });
    }
}