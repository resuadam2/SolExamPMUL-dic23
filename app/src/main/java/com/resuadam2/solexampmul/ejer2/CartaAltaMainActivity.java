package com.resuadam2.solexampmul.ejer2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.resuadam2.solexampmul.R;

public class CartaAltaMainActivity extends AppCompatActivity implements FrgCarta.OnCartaRobadaListener {

    int jugadores; // Número de jugadores
    FrgCarta[] cartas;// Array de fragmentos
    boolean debug = true;// Modo debug
    TextView tvGanador;// TextView para mostrar el ganador
    int jugaron = 0; // Número de jugadores que han jugado
    Button btnJugar; // Botón para empezar a jugar
    int ids[] = {R.id.carta1, R.id.carta2, R.id.carta3, R.id.carta4}; // Array de ids de los fragmentos
    LinearLayout player1, player2, player3, player4; // Layouts de los jugadores
    Spinner numJugadores; // Spinner para elegir el número de jugadores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carta_alta);
        tvGanador = findViewById(R.id.tvWinner);
        btnJugar = findViewById(R.id.btnStart);
        player1 = findViewById(R.id.player1);
        player1.setVisibility(View.GONE);
        player2 = findViewById(R.id.player2);
        player2.setVisibility(View.GONE);
        player3 = findViewById(R.id.player3);
        player3.setVisibility(View.GONE);
        player4 = findViewById(R.id.player4);
        player4.setVisibility(View.GONE);
        numJugadores = findViewById(R.id.spnNumPlayers);
        numJugadores.setSelection(0);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numJugadores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numJugadores.setAdapter(adapter);
        numJugadores.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jugadores = Integer.parseInt(numJugadores.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnJugar.setOnClickListener(v -> {
            jugadores = Integer.parseInt(numJugadores.getSelectedItem().toString());
            cartas = new FrgCarta[jugadores];
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < jugadores; i++) {
                cartas[i] = (FrgCarta) fm.findFragmentById(ids[i]);
                cartas[i].setOnCartaRobadaListener(this, debug);
                cartas[i].jugando(true);
            }
            jugadoresVisibles();
            jugaron = 0;
        });

    }

    /**
     * Muestra los jugadores que van a jugar según el número de jugadores
     * seleccionado en el spinner
     */
   private void jugadoresVisibles() {
        switch (jugadores) {
            case 2:
                player1.setVisibility(View.VISIBLE);
                player2.setVisibility(View.VISIBLE);
                player3.setVisibility(View.GONE);
                player4.setVisibility(View.GONE);
                break;
            case 3:
                player1.setVisibility(View.VISIBLE);
                player2.setVisibility(View.VISIBLE);
                player3.setVisibility(View.VISIBLE);
                player4.setVisibility(View.GONE);
                break;
            case 4:
                player1.setVisibility(View.VISIBLE);
                player2.setVisibility(View.VISIBLE);
                player3.setVisibility(View.VISIBLE);
                player4.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * Comprueba si todos los jugadores han jugado
     * @return true si todos los jugadores han jugado
     */
    private boolean todosJugaron() {
        return jugaron == jugadores;
    }

    private boolean cartaDisponible(int jugador, String palo, int num) {
        for (int i = 0; i < jugadores; i++) {
            if(jugador != i) {
                if (cartas[i].getPalo().equals(palo) && cartas[i].getNumero() == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Devuelve el jugador al que pertenece el fragmento
     * @param carta fragmento
     * @return jugador
     */
    private int getJugador(FrgCarta carta) {
        for (int i = 0; i < jugadores; i++) {
            if (cartas[i].equals(carta)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que se ejecuta cuando se roba una carta
     * @param fragmento fragmento que ha robado la carta
     * @param robada true si la carta ha sido robada
     * @return true si la carta ha sido robada
     */
    @Override
    public boolean onCartaRobada(FrgCarta fragmento, boolean robada) {
        if (!cartaDisponible(getJugador(fragmento), fragmento.getPalo(), fragmento.getNumero())) {
            fragmento.jugando(true);
            return false;
        }
        if (robada) {
            jugaron++;
            if (todosJugaron()) {
                int ganador = 0;
                for (int i = 1; i < jugadores; i++) {
                    if (cartas[i].getNumero() > cartas[ganador].getNumero()) {
                        ganador = i;
                    } else if (cartas[i].getNumero() == cartas[ganador].getNumero()) {
                        if (cartas[i].paloMayor(cartas[ganador].getPalo())) {
                            ganador = i;
                        }
                    }
                }
                tvGanador.setText(getResources().getString(R.string.winner) + "\n" + (ganador + 1));
                tvGanador.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                Snackbar.make(findViewById(R.id.activity_main_carta_alta), getResources().getString(R.string.winner) + (ganador + 1), Snackbar.LENGTH_LONG).show();
                for (int i = 0; i < jugadores; i++) {
                    cartas[i].jugando(false);
                }
            }
            fragmento.jugando(false);
            return true;
        }
        return false;
    }
}