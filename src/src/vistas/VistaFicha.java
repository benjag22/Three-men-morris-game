package vistas;

import logica.Ficha;

import javax.swing.*;
import java.awt.*;

public class VistaFicha extends JPanel {
    private Ficha ficha;

    public VistaFicha(Ficha ficha) {
        this.ficha = ficha;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        switch (ficha.getColor().toLowerCase()) {
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

        g2d.fillOval(x, y, diameter, diameter);
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
        repaint();
    }
}
