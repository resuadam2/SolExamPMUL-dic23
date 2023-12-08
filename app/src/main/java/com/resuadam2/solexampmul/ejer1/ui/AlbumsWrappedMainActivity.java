package com.resuadam2.solexampmul.ejer1.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.resuadam2.solexampmul.R;
import com.resuadam2.solexampmul.ejer1.db.DBManager;
import com.resuadam2.solexampmul.ejer1.model.Album;

import java.util.ArrayList;

public class AlbumsWrappedMainActivity extends AppCompatActivity {
    FloatingActionButton btnAddAlbum; // Botón para añadir un album
    TextView btnAlbum, btnArtista, btnNumCanciones, btnValoracion; // Botones para ordenar la lista
    ListView lvAlbums; // Lista de albumes
    AlbumsAdapter adapter; // Adaptador de la lista
    ArrayList<Album> albums; // Lista de albumes a mostrar
    DBManager db; // Base de datos
    String orderBy; // Columna por la que se ordena
    String order; // Orden ascendente o descendente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_albums_wrapped);

        btnAlbum = findViewById(R.id.btnAlbum);
        btnArtista = findViewById(R.id.btnArtista);
        btnNumCanciones = findViewById(R.id.btnNumCanciones);
        btnValoracion = findViewById(R.id.btnValoracion);
        lvAlbums = findViewById(R.id.lvAlbums);
        albums = new ArrayList<>();
        db = new DBManager(this);
        adapter = new AlbumsAdapter(this, albums, R.layout.album_item);
        lvAlbums.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        orderBy = prefs.getString("orderBy", DBManager.COLUMN_TITULO);
        order = prefs.getString("order", DBManager.ORDER_ASC);

        changeOrder(orderBy);

        btnAlbum.setOnClickListener(v -> changeOrder(DBManager.COLUMN_TITULO));
        btnArtista.setOnClickListener(v -> changeOrder(DBManager.COLUMN_ARTISTA));
        btnNumCanciones.setOnClickListener(v -> changeOrder(DBManager.COLUMN_NUM_CANCIONES));
        btnValoracion.setOnClickListener(v -> changeOrder(DBManager.COLUMN_VALORACION));

        btnAddAlbum = findViewById(R.id.btAdd);

        btnAddAlbum.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAlbum.class);
            intent.putExtra("id", -1);
            startActivity(intent);
        });

        lvAlbums.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, AddAlbum.class);
            intent.putExtra("id", albums.get(position).getId());
            startActivity(intent);
        });

        lvAlbums.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.delete) + " " + albums.get(position).getTitulo() + "?");
            builder.setPositiveButton(getResources().getString(R.string.yes), (dialog, which) -> {
                db.eliminarAlbum(albums.get(position).getTitulo(), albums.get(position).getArtista());
                loadAlbumes(orderBy, order);
            });
            builder.setNegativeButton(getResources().getString(R.string.no), null);
            builder.create().show();
            return true;
        });


    }

    /**
     * Método que se ejecuta al volver a la actividad
     * Carga los albumes de la base de datos
     * Carga de las preferencias el orden y la columna por la que se ordena
     */
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        String orderByPref = prefs.getString("orderBy", DBManager.COLUMN_TITULO);
        String orderPref = prefs.getString("order", DBManager.ORDER_ASC);
        loadAlbumes(orderByPref, orderPref);
    }

    /**
     * Método que se ejecuta al pausar la actividad (al salir de ella)
     * Guarda en las preferencias el orden y la columna por la que se ordena
     */
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("preferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("orderBy", orderBy);
        editor.putString("order", order);
        editor.apply();
    }

    /**
     * Método que cambia el orden de la lista
     * @param orderByPref columna por la que se ordena
     */
    private void changeOrder(String orderByPref) {
        if (orderByPref.equals(DBManager.COLUMN_TITULO)) {
            btnAlbum.setBackgroundColor(getResources().getColor(R.color.selected_tv_order));
            btnArtista.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnNumCanciones.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnValoracion.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            orderBy = DBManager.COLUMN_TITULO;
        } else if (orderByPref.equals(DBManager.COLUMN_ARTISTA)) {
            btnAlbum.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnArtista.setBackgroundColor(getResources().getColor(R.color.selected_tv_order));
            btnNumCanciones.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnValoracion.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            orderBy = DBManager.COLUMN_ARTISTA;
        } else if (orderByPref.equals(DBManager.COLUMN_NUM_CANCIONES)) {
            btnAlbum.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnArtista.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnNumCanciones.setBackgroundColor(getResources().getColor(R.color.selected_tv_order));
            btnValoracion.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            orderBy = DBManager.COLUMN_NUM_CANCIONES;
        } else if (orderByPref.equals(DBManager.COLUMN_VALORACION)) {
            btnAlbum.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnArtista.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnNumCanciones.setBackgroundColor(getResources().getColor(R.color.background_tv_order));
            btnValoracion.setBackgroundColor(getResources().getColor(R.color.selected_tv_order));
            orderBy = DBManager.COLUMN_VALORACION;
        }
        if(order.equals(DBManager.ORDER_ASC)) {
            loadAlbumes(orderBy, DBManager.ORDER_ASC);
            changeTextButtonsOrder();
            order = DBManager.ORDER_DESC;
        } else {
            loadAlbumes(orderBy, DBManager.ORDER_DESC);
            changeTextButtonsOrder();
            order = DBManager.ORDER_ASC;
        }
    }

    /**
     * Método que cambia el texto de los botones de orden
     */
    private void changeTextButtonsOrder() {
        if(order.equals(DBManager.ORDER_ASC)) {
            btnAlbum.setText("Album ▲");
            btnArtista.setText("Artista ▲");
            btnNumCanciones.setText("Num. Canciones ▲");
            btnValoracion.setText("Valoración ▲");
        } else {
            btnAlbum.setText("Album ▼");
            btnArtista.setText("Artista ▼");
            btnNumCanciones.setText("Num. Canciones ▼");
            btnValoracion.setText("Valoración ▼");
        }
    }


    /**
     * Método que carga los albumes de la base de datos
     * @param orderBy columna por la que se ordena
     * @param order orden
     */
    private void loadAlbumes(String orderBy, String order) {
        Cursor cursor = db.getAlbumesOrderBy(orderBy, order);
        albums.clear();
        loadAlbumesFromCursor(cursor);
    }

    /**
     * Método que carga los albumes de un cursor
     * @param cursor cursor
     */
    private void loadAlbumesFromCursor(Cursor cursor) {
        while(cursor.moveToNext()) {
            albums.add(new Album(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4)));
        }
        adapter.notifyDataSetChanged();
    }
}