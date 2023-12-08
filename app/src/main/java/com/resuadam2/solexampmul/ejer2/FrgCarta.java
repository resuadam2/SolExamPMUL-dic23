package com.resuadam2.solexampmul.ejer2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.resuadam2.solexampmul.R;

public class FrgCarta  extends Fragment {
    Button btnCarta, btnCartaSpinners;
    Spinner spnPalo, spnNumero;
    OnCartaRobadaListener listener;
    TextView tvResultado;
    boolean debug, robada = false;
    /**
     * Array de palos
     */
    String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};
    /**
     * Array de números
     */
    String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "Sota", "Caballo", "Rey"};

    /**
     * Interfaz para comunicar el fragmento con la actividad
     */
    public interface OnCartaRobadaListener {
        boolean onCartaRobada(FrgCarta fragmento, boolean robada);
    }

    /**
     * Método para comprobar si la carta ha sido robada
     */
    public boolean comprobarRobada() {
        return robada;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void setRobada(boolean robada) {
        this.robada = robada;
    }

    public void cartaRobada(String palo, String numero) {
        robada = true;
        if (debug) {
            Log.i("cartaRobada", "Palo: " + palo + " Número: " + numero);
            btnCartaSpinners.setEnabled(false);
            spnNumero.setEnabled(false);
            spnPalo.setEnabled(false);
        }
        tvResultado.setText(tvResultado.getText().toString() + "\nPalo: " + palo + "\nNúmero: " + numero);
        btnCarta.setEnabled(false);
    }

    public void setOnCartaRobadaListener(OnCartaRobadaListener listener, boolean debug) {
        this.listener = listener;
        this.debug = debug;
    }

    public void jugando(boolean jugando) {
        if (jugando) {
            btnCarta.setEnabled(true);
            spnPalo.setEnabled(true);
            spnNumero.setEnabled(true);
        } else {
            btnCarta.setEnabled(false);
            spnPalo.setEnabled(false);
            spnNumero.setEnabled(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_carta, container, false);
        btnCarta = view.findViewById(R.id.button);
        tvResultado = view.findViewById(R.id.tvResultado);
        if (debug) {
            spnPalo = view.findViewById(R.id.spnPalo);
            spnNumero = view.findViewById(R.id.spnCarta);
            ArrayAdapter<String> adaptadorPalos = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, palos);
            ArrayAdapter<String> adaptadorNumeros = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, numeros);
            spnPalo.setAdapter(adaptadorPalos);
            spnNumero.setAdapter(adaptadorNumeros);
            btnCartaSpinners = view.findViewById(R.id.btnSpinners);
            btnCartaSpinners.setOnClickListener(v -> {
                cartaRobada(spnPalo.getSelectedItem().toString(), spnNumero.getSelectedItem().toString());
            });
        }
        btnCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartaRobada(palos[(int) (Math.random() * palos.length)], numeros[(int) (Math.random() * numeros.length)]);
            }
        });
        return view;
    }

}
