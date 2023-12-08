package com.resuadam2.solexampmul.ejer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.resuadam2.solexampmul.R;

public class CartaAltaMainActivity extends AppCompatActivity implements FrgCarta.OnCartaRobadaListener {

    EditText numJugadores;
    int jugadores;
    FrgCarta[] cartas;
    CartasAdapter adaptador;
    boolean debug = true;
    TextView tvGanador;
    ListView lvCartas;
    int jugaron = 0;
    Button btnJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_carta_alta);
        numJugadores = findViewById(R.id.numJugadores);
        numJugadores.setText("2");
        tvGanador = findViewById(R.id.tvWinner);
        lvCartas = findViewById(R.id.lvCartas);
        btnJugar = findViewById(R.id.btnStart);

        numJugadores.setOnClickListener(v -> {
            NumberPicker np = new NumberPicker(this);
            np.setMinValue(2);
            np.setMaxValue(4);
            np.setWrapSelectorWheel(false);
            np.setOnValueChangedListener((picker, oldVal, newVal) -> numJugadores.setText(String.valueOf(newVal)));
        });

        btnJugar.setOnClickListener(v -> {
            jugadores = Integer.parseInt(numJugadores.getText().toString());
            cartas = new FrgCarta[jugadores];
            for (int i = 0; i < jugadores; i++) {
                cartas[i] = new FrgCarta();
                cartas[i].setDebug(debug);
            }
            adaptador = new CartasAdapter(cartas);
            lvCartas.setAdapter(adaptador);
            jugaron = 0;
        });

    }

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

    private int getJugador(FrgCarta carta) {
        for (int i = 0; i < jugadores; i++) {
            if (cartas[i] == carta) {
                return i;
            }
        }
        return -1;
    }

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
                tvGanador.setText("Ganador:\n" + (ganador + 1));
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