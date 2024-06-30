package vistas;

import logica.Ficha;
import logica.Juego;
import logica.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaJuego extends JPanel {
    private Juego juego;
    private VistaTablero vistaTablero;
    private VistaJugador vistaJugador1;
    private VistaJugador vistaJugador2;
    private Jugador currentPlayer;

    public VistaJuego() {
        this.juego = new Juego();
        this.vistaTablero = new VistaTablero(juego.getTab());
        this.vistaJugador1 = new VistaJugador(juego.getJugador1());
        this.vistaJugador2 = new VistaJugador(juego.getJugador2());
        this.currentPlayer = juego.getJugador1();

        setLayout(new BorderLayout());

        add(vistaTablero, BorderLayout.CENTER);
        add(vistaJugador1, BorderLayout.NORTH);
        add(vistaJugador2, BorderLayout.SOUTH);

        addListeners();
    }

    private void addListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int x = i;
                final int y = j;
                vistaTablero.getCelda(x, y).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleCellClick(x, y);
                    }
                });
            }
        }
    }

    private void handleCellClick(int x, int y) {
        Ficha ficha = new Ficha(currentPlayer.getColor());
        boolean success = vistaJugador1.ponerFicha(vistaTablero, x, y); // Cambiar a vistaJugador1 o vistaJugador2 según corresponda
        if (!success) {
            JOptionPane.showMessageDialog(this, "Movimiento invalido en la posición (" + x + ", " + y + ").");
            return;
        }

        if (juego.getTab().verificarVictoria(currentPlayer.getColor())) {
            JOptionPane.showMessageDialog(this, "Jugador " + currentPlayer.getColor() + " ha ganado!");
            reiniciarJuego();
            return;
        }

        currentPlayer = (currentPlayer == juego.getJugador1()) ? juego.getJugador2() : juego.getJugador1();
    }


    private void reiniciarJuego() {
        juego = new Juego();
        currentPlayer = juego.getJugador1();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Three Men's Morris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        VistaJuego vistaJuego = new VistaJuego();
        frame.add(vistaJuego);

        frame.setVisible(true);
    }
}
