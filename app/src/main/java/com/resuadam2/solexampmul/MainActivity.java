package com.resuadam2.solexampmul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.resuadam2.solexampmul.ejer1.ui.Ejer1Acivity;
import com.resuadam2.solexampmul.ejer2.Ejer2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEjer1 = findViewById(R.id.btnEjer1);
        Button btnEjer2 = findViewById(R.id.btnEjer2);

        btnEjer1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ejer1Acivity.class);
            startActivity(intent);
        });

        btnEjer2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Ejer2Activity.class);
            startActivity(intent);
        });
    }
}