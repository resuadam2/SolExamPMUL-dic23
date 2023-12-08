package com.resuadam2.solexampmul.ejer1.ui;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.resuadam2.solexampmul.R;
import com.resuadam2.solexampmul.ejer1.model.Album;

import java.util.ArrayList;

public class AlbumsAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<Album> albums;

    private int layout;

    public static int selected = -1;


    @Override
    public int getCount() {
        return albums.size(); // Devuelve el tamaño de la lista
    }

    @Override
    public Album getItem(int i) {
        return albums.get(i); // Devuelve el elemento de la posición i
    }

    @Override
    public long getItemId(int i) {
        return albums.get(i).getId(); // Devuelve el id del elemento de la posición i
    }

    /**
     * Devuelve la vista que se va a mostrar en la posición i
     * @param i posición
     * @param view vista
     * @param viewGroup grupo de vistas
     * @return vista
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) { // Si la vista es null, la creamos
            view = View.inflate(context, layout, null);
        }

        TextView tvTitulo = view.findViewById(R.id.tvAlbum);
        TextView tvArtista = view.findViewById(R.id.tvArtista);
        TextView tvValoracion = view.findViewById(R.id.tvValoracion);
        tvTitulo.setText(albums.get(i).getTitulo());
        tvArtista.setText(albums.get(i).getArtista());
        tvValoracion.setText("");
        for(int j = 1; j <= 5; j++) {
            if(j <= albums.get(i).getValoracion())
                tvValoracion.setText(tvValoracion.getText() + getContext().getString(R.string.star));
            else
                tvValoracion.setText(tvValoracion.getText() + getContext().getString(R.string.star_border));
        }
        return view;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public int getLayout() {
        return layout;
    }

    public AlbumsAdapter(Context context, ArrayList<Album> albums, int layout) {
        this.context = context;
        this.albums = albums;
        this.layout = layout;
    }

    public AlbumsAdapter() {
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void removeAlbum(int position) {
        albums.remove(position);
    }

    public void clear() {
        albums.clear();
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
