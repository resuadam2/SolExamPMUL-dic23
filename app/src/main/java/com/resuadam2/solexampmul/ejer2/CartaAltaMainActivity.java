package com.resuadam2.solexampmul.ejer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.resuadam2.solexampmul.R;

public class CartaAltaMainActivity extends AppCompatActivity implements FrgCarta.OnCartaRobadaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carta_alta);
    }

    @Override
    public boolean onCartaRobada(FrgCarta fragmento, boolean robada) {
        return false;
    }
}