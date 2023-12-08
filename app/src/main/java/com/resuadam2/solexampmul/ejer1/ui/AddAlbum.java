package com.resuadam2.solexampmul.ejer1.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.resuadam2.solexampmul.R;
import com.resuadam2.solexampmul.ejer1.db.DBManager;
import com.resuadam2.solexampmul.ejer1.model.Album;

public class AddAlbum extends AppCompatActivity {
    EditText etTitulo, etArtista, etNumCanciones;
    TextView star1, star2, star3, star4, star5;
    Button btnOk, btnCancel;

    DBManager db;

    int valoracion = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);

        etTitulo = findViewById(R.id.etAlbum);
        etArtista = findViewById(R.id.etArtista);
        etNumCanciones = findViewById(R.id.etNumCanciones);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        db = new DBManager(this);

        star1.setOnClickListener(v -> {
            valoracion = 1;
            updateViewValoracion();
        });
        star2.setOnClickListener(v -> {
            valoracion = 2;
            updateViewValoracion();
        });
        star3.setOnClickListener(v -> {
            valoracion = 3;
            updateViewValoracion();
        });
        star4.setOnClickListener(v -> {
            valoracion = 4;
            updateViewValoracion();
        });
        star5.setOnClickListener(v -> {
            valoracion = 5;
            updateViewValoracion();
        });

        if (getIntent().getExtras().getInt("id") != -1) {
            int id = getIntent().getExtras().getInt("id");
            Album album = db.getAlbum(id);
            etTitulo.setText(album.getTitulo());
            etTitulo.setEnabled(false);
            etArtista.setText(album.getArtista());
            etArtista.setEnabled(false);
            etNumCanciones.setText(String.valueOf(album.getNumCanciones()));
            etNumCanciones.setEnabled(false);
            valoracion = album.getValoracion();
            updateViewValoracion();
        } else {
            etNumCanciones.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() != 0 && Integer.parseInt(s.toString()) > 2) {
                        btnOk.setEnabled(true);
                    } else {
                        btnOk.setEnabled(false);
                        Toast.makeText(AddAlbum.this, "El número de canciones debe ser mayor que 2", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            btnOk.setEnabled(false);

        }

        btnOk.setOnClickListener(v -> {
            String titulo = etTitulo.getText().toString();
            String artista = etArtista.getText().toString();
            int numCanciones = Integer.parseInt(etNumCanciones.getText().toString());
            db.insertarAlbum(titulo, artista, numCanciones, valoracion); // Si el album ya existe, no se inserta, solo actualiza la valoración
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }

    private void updateViewValoracion() {
        if (valoracion == 1) {
            star1.setText(getResources().getString(R.string.star));
            star2.setText(getResources().getString(R.string.star_border));
            star3.setText(getResources().getString(R.string.star_border));
            star4.setText(getResources().getString(R.string.star_border));
            star5.setText(getResources().getString(R.string.star_border));
        } else if (valoracion == 2) {
            star1.setText(getResources().getString(R.string.star));
            star2.setText(getResources().getString(R.string.star));
            star3.setText(getResources().getString(R.string.star_border));
            star4.setText(getResources().getString(R.string.star_border));
            star5.setText(getResources().getString(R.string.star_border));
        } else if (valoracion == 3) {
            star1.setText(getResources().getString(R.string.star));
            star2.setText(getResources().getString(R.string.star));
            star3.setText(getResources().getString(R.string.star));
            star4.setText(getResources().getString(R.string.star_border));
            star5.setText(getResources().getString(R.string.star_border));
        } else if (valoracion == 4) {
            star1.setText(getResources().getString(R.string.star));
            star2.setText(getResources().getString(R.string.star));
            star3.setText(getResources().getString(R.string.star));
            star4.setText(getResources().getString(R.string.star));
            star5.setText(getResources().getString(R.string.star_border));
        } else if (valoracion == 5) {
            star1.setText(getResources().getString(R.string.star));
            star2.setText(getResources().getString(R.string.star));
            star3.setText(getResources().getString(R.string.star));
            star4.setText(getResources().getString(R.string.star));
            star5.setText(getResources().getString(R.string.star));
        }
    }


}
