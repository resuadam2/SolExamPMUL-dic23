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

import java.util.ArrayList;
import java.util.Arrays;

public class FrgCarta  extends Fragment {
    Button btnCarta, btnCartaSpinners; // Botones para robar carta y para robar carta con spinners
    Spinner spnPalo, spnNumero; // Spinners para elegir palo y número
    OnCartaRobadaListener listener; // Listener para comunicar el fragmento con la actividad
    TextView tvResultado; // TextView para mostrar el resultado
    boolean debug = true, robada = false; // Modo debug y si la carta ha sido robada
    /**
     * Array de palos
     */
    String[] palos = {"Bastos", "Espadas", "Copas", "Oros"};
    /**
     * Array de números
     */
    String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "Sota", "Caballo", "Rey"};

    String[] carta = new String[2];

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

    public String[] getCarta() {
        return carta;
    }

    public void setCarta(String[] carta) {
        this.carta = carta;
    }

    /**
     * Método para obtener el número de la carta robada en el fragmento
     * @return Número de la carta
     */
    public int getNumero() {
        if (carta[1].equals("Sota"))
            return 10;
        if (carta[1].equals("Caballo"))
            return 11;
        if (carta[1].equals("Rey"))
            return 12;
        return Integer.parseInt(carta[1]);
    }

    public String getPalo() {
        return carta[0];
    }

    /**
     * Método para comprobar si la carta robada es mayor que la carta del fragmento
     * el orden de importancia de los palos es, de menor a mayor: Bastos, Espadas, Copas, Oros
     * @param palo palo de la carta
     * @return true si la carta robada es mayor
     */
    public boolean paloMayor(String palo) {
        int paloCarta = 0, paloParam = 0;
        for (int i = 0; i < palos.length; i++) {
            if (palo.equals(palos[i])) {
                paloParam = i;
            }
            if (carta[0].equals(palos[i])) {
                paloCarta = i;
            }
        }
        return paloCarta > paloParam;
    }

    /**
     * Método para robar una carta
     * @param palo palo de la carta
     * @param numero número de la carta
     */
    public void cartaRobada(String palo, String numero) {
        robada = true;
        if (debug) {
            Log.i("cartaRobada", "Palo: " + palo + " Número: " + numero);
        }
        tvResultado.setText(getResources().getString(R.string.resultado) + "\nPalo: " + palo + "\nNúmero: " + numero);
        carta[0] = palo;
        carta[1] = numero;
        if (listener != null) {
            listener.onCartaRobada(this, robada);
        }
    }

    /**
     * Método para establecer el listener del fragmento y el modo debug del fragmento
     * @param listener listener
     * @param debug modo debug
     */
    public void setOnCartaRobadaListener(OnCartaRobadaListener listener, boolean debug) {
        this.listener = listener;
        this.carta = new String[]{"-1", "-1"};
        this.debug = debug;
        if (debug) {
            ArrayAdapter<String> adaptadorPalos = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(palos));
            ArrayAdapter<String> adaptadorNumeros = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(numeros));
            adaptadorPalos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPalo.setAdapter(adaptadorPalos);
            adaptadorNumeros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnNumero.setAdapter(adaptadorNumeros);
        }
    }

    /**
     * Método para establecer si el fragmento está jugando
     * @param jugando true si está jugando
     */
    public void jugando(boolean jugando) {
        if (jugando) {
            btnCarta.setEnabled(true);
            spnPalo.setEnabled(true);
            spnNumero.setEnabled(true);
            btnCartaSpinners.setEnabled(true);
        } else {
            btnCarta.setEnabled(false);
            spnPalo.setEnabled(false);
            spnNumero.setEnabled(false);
            btnCartaSpinners.setEnabled(false);
        }
    }

    /**
     * Método que se ejecuta cuando se crea el fragmento
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return View del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_carta, container, false);
        btnCarta = view.findViewById(R.id.button);
        tvResultado = view.findViewById(R.id.tvResultado);
        if (debug) {
            spnPalo = view.findViewById(R.id.spnPalo);
            spnNumero = view.findViewById(R.id.spnCarta);
            btnCartaSpinners = view.findViewById(R.id.btnSpinners);
            btnCartaSpinners.setOnClickListener(v -> {
                cartaRobada(spnPalo.getSelectedItem().toString(), spnNumero.getSelectedItem().toString());
            });
        }
        btnCarta.setOnClickListener(v -> cartaRobada(palos[(int) (Math.random() * palos.length)], numeros[(int) (Math.random() * numeros.length)]));
        return view;
    }

}
