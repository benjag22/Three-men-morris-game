package vistas;

import logica.Ficha;
import logica.Tablero;

import javax.swing.*;
import java.awt.*;

public class VistaTablero extends JPanel {
    private Tablero tablero;
    private VistaFicha[][] vistaFichas;

    public VistaTablero(Tablero tablero) {
        this.tablero = tablero;
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new GridLayout(3, 3));
        inicializarVistaFichas();
    }

    private void inicializarVistaFichas() {
        vistaFichas = new VistaFicha[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vistaFichas[i][j] = new VistaFicha(tablero.getTablero()[i][j]);
                this.add(vistaFichas[i][j]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / 3;
        int cellHeight = height / 3;

        for (int i = 0; i <= 3; i++) {
            g2d.drawLine(i * cellWidth, 0, i * cellWidth, height);
            g2d.drawLine(0, i * cellHeight, width, i * cellHeight);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                VistaFicha vistaFicha = vistaFichas[i][j];
                if (!vistaFicha.getFicha().getColor().equals("gris")) {
                    switch (vistaFicha.getFicha().getColor().toLowerCase()) {
                        case "rojo":
                            g2d.setColor(Color.RED);
                            break;
                        case "azul":
                            g2d.setColor(Color.BLUE);
                            break;
                        default:
                            g2d.setColor(Color.GRAY);
                            break;
                    }
                    int x = j * cellWidth;
                    int y = i * cellHeight;
                    g2d.fillOval(x + 10, y + 10, cellWidth - 20, cellHeight - 20);
                }
            }
        }
    }

    public void actualizar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vistaFichas[i][j].setFicha(tablero.getTablero()[i][j]);
            }
        }
        repaint();
    }

    public VistaFicha getCelda(int x, int y) {
        return vistaFichas[x][y];
    }

    public boolean ocuparPosicion(int x, int y, Ficha ficha) {
        boolean result = tablero.ocuparPosicion(x, y, ficha);
        if (result) {
            vistaFichas[x][y].setFicha(ficha);
            actualizar();
        }
        return result;
    }

    public boolean verificarEspacio(int x, int y) {
        return tablero.verificarEspacio(x, y);
    }

    public boolean moverFicha(int x, int y, int newX, int newY, String color) {
        boolean result = tablero.moverFicha(x, y, newX, newY, color);
        if (result) {
            vistaFichas[newX][newY].setFicha(vistaFichas[x][y].getFicha());
            vistaFichas[x][y].setFicha(new Ficha());
            actualizar();
        }
        return result;
    }
}
