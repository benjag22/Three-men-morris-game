package vistas;

import logica.Jugador;
import logica.Ficha;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaJugador extends JPanel {
    private Jugador jugador;

    public VistaJugador(String color) {
        this.jugador = new Jugador(color);
        this.setLayout(new GridLayout(1, 3));
        actualizarVista();
    }

    public void actualizarVista() {
        this.removeAll();
        List<Ficha> fichas = jugador.getFichas();
        for (Ficha ficha : fichas) {
            this.add(new VistaFicha(ficha));
        }
        this.revalidate();
        this.repaint();
    }
}
