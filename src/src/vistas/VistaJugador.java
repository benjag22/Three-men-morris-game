package vistas;

import logica.Ficha;
import logica.Jugador;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VistaJugador extends JPanel {
    private Jugador jugador;
    private List<Pair<Integer, Integer>> posicionesActuales;

    public VistaJugador(Jugador jugador) {
        this.jugador = jugador;
        this.posicionesActuales = new ArrayList<>();
        actualizarVista();
    }

    public void actualizarVista() {
        removeAll();
        setLayout(new GridLayout(2, 1));

        JPanel fichasDisponiblesPanel = new JPanel();
        fichasDisponiblesPanel.setBorder(BorderFactory.createTitledBorder("Fichas Disponibles"));
        List<Ficha> fichas = jugador.getFichas();
        for (Ficha ficha : fichas) {
            fichasDisponiblesPanel.add(new VistaFicha(ficha));
        }
        add(fichasDisponiblesPanel);

        JPanel fichasColocadasPanel = new JPanel();
        fichasColocadasPanel.setBorder(BorderFactory.createTitledBorder("Fichas Colocadas"));
        for (Pair<Integer, Integer> posicion : posicionesActuales) {
            JLabel label = new JLabel("(" + posicion.first + ", " + posicion.second + ")");
            fichasColocadasPanel.add(label);
        }
        add(fichasColocadasPanel);

        revalidate();
        repaint();
    }

    public boolean ponerFicha(VistaTablero tab, int x, int y) {
        if (jugador.getFichas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No te quedan fichas para insertar.");
            return false;
        }
        Ficha ficha = jugador.getFichas().remove(jugador.getFichas().size() - 1);
        boolean result = tab.ocuparPosicion(x, y, ficha);
        if (result) {
            posicionesActuales.add(new Pair<>(x, y)); // Agregar la posición de la ficha
        } else {
            jugador.getFichas().add(ficha);
        }
        actualizarVista();
        return result;
    }

    public boolean moverFicha(VistaTablero tab, int x, int y, int newX, int newY) {
        Pair<Integer, Integer> fichaPosicion = buscarFicha(x, y);
        if (fichaPosicion == null) {
            JOptionPane.showMessageDialog(this, "No hay ficha en la posición (" + x + ", " + y + ").");
            return false;
        }

        boolean result = tab.moverFicha(x, y, newX, newY, jugador.getColor());
        if (result) {
            fichaPosicion.setFirst(newX);
            fichaPosicion.setSecond(newY);
            actualizarVista();
        }
        return result;
    }

    private Pair<Integer, Integer> buscarFicha(int x, int y) {
        for (Pair<Integer, Integer> posicion : posicionesActuales) {
            if (posicion.first == x && posicion.second == y) {
                return posicion;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 30;
        int spacing = 80;

        // Dibujar las fichas disponibles del jugador
        List<Ficha> fichas = jugador.getFichas();
        for (Ficha ficha : fichas) {
            Color color = getColorFromJugador(jugador);
            g.setColor(color);
            g.fillOval(x, y, 50, 50);
            x += spacing;
        }

        // Dibujar las fichas colocadas en el tablero
        for (Pair<Integer, Integer> posicion : posicionesActuales) {
            Color color = getColorFromJugador(jugador);
            g.setColor(color);
            g.fillOval(posicion.first * spacing + x, posicion.second * spacing + y, 50, 50);
            x += spacing;
        }
    }

    private Color getColorFromJugador(Jugador jugador) {
        switch (jugador.getColor().toLowerCase()) {
            case "rojo":
                return Color.RED;
            case "azul":
                return Color.BLUE;
            default:
                return Color.GRAY;
        }
    }

    public Jugador getJugador() {
        return jugador;
    }
}
