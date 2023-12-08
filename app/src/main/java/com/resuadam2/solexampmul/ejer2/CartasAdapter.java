package com.resuadam2.solexampmul.ejer2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CartasAdapter extends BaseAdapter {
    private FrgCarta[] cartas;

    public CartasAdapter(FrgCarta[] cartas) {
        this.cartas = cartas;
    }

    @Override
    public int getCount() {
        return cartas.length;
    }

    @Override
    public Object getItem(int position) {
        return cartas[position];
    }

    @Override
    public long getItemId(int position) {
        return cartas[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return cartas[position].getView();
    }
}
