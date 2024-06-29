package vistas;

import logica.Juego;
import logica.Tablero;
import logica.Ficha;
import logica.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaJuego extends JFrame {
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJugador vistaJugador1;
    private VistaJugador vistaJugador2;
    private Jugador currentPlayer;

    public VistaJuego() {
        this.juego = new Juego();
        this.vistaTablero = new VistaTablero(juego.getTab());
        this.vistaJugador1 = new VistaJugador(juego.getJugador1().getColor());
        this.vistaJugador2 = new VistaJugador(juego.getJugador2().getColor());
        this.currentPlayer = juego.getJugador1();

        setLayout(new BorderLayout());

        add(vistaTablero, BorderLayout.CENTER);
        add(vistaJugador1, BorderLayout.NORTH);
        add(vistaJugador2, BorderLayout.SOUTH);

        setTitle("Three Men's Morris");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addListeners();
    }

    private void addListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int x = i;
                final int y = j;
                vistaTablero.getVistaFicha(x, y).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleCellClick(x, y);
                    }
                });
            }
        }
    }

    private void handleCellClick(int x, int y) {
        if (juego.getTab().verificarEspacio(x, y)) {
            Ficha ficha = new Ficha(currentPlayer.getColor());
            vistaTablero.ocuparPosicion(x, y, ficha);
            if (juego.getTab().verificarVictoria(currentPlayer.getColor())) {
                JOptionPane.showMessageDialog(this, "Jugador " + currentPlayer.getColor() + " ha ganado!");
                return;
            }
            currentPlayer = (currentPlayer == juego.getJugador1()) ? juego.getJugador2() : juego.getJugador1();
        }
    }

    public static void main(String[] args) {
        new VistaJuego();
    }
}
