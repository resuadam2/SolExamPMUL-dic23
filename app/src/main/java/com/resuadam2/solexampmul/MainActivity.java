package com.resuadam2.solexampmul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.resuadam2.solexampmul.ejer1.ui.AlbumsWrappedMainActivity;
import com.resuadam2.solexampmul.ejer2.CartaAltaMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEjer1 = findViewById(R.id.btnEjer1);
        Button btnEjer2 = findViewById(R.id.btnEjer2);

        btnEjer1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlbumsWrappedMainActivity.class);
            startActivity(intent);
        });

        btnEjer2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartaAltaMainActivity.class);
            startActivity(intent);
        });
    }
}