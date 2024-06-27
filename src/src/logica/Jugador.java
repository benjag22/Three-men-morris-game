package logica;

import java.util.ArrayList;

public class Jugador {
    private String color;
    private int fichasDisp;
    private ArrayList<Ficha> Fichas;

    public Jugador(String color) {
        this.color = color;
        this.fichasDisp = 3;
        this.Fichas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Fichas.add(new Ficha(color));
        }
    }

    public String getColor() {
        return color;
    }

    public boolean ponerFicha(Tablero tab, int x, int y) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            System.out.println("Fuera de los límites del tablero");
            return false;
        } else if (!tab.ocuparPosicion(x, y, new Ficha(color))) {
            return false;
        } else if (fichasDisp == 0) {
            System.out.println("No te quedan fichas que insertar");
            return false;
        } else {
            fichasDisp--;
            return true;
        }
    }

    public boolean moverFicha(Tablero tab, int x, int y, int newX, int newY) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3 || newX < 0 || newX >= 3 || newY < 0 || newY >= 3) {
            System.out.println("Fuera de los límites del tablero");
            return false;
        } else {
            return tab.moverFicha(x, y, newX, newY, color);
        }
    }
}
