package logica;

public class Tablero {
    private Ficha[][] tablero;
    private int numOcupados;
    private int numDesocupados;

    public Tablero() {
        this.tablero = new Ficha[3][3];
        vaciarTablero();
        this.numDesocupados = 9;
        this.numOcupados = 0;
    }

    public Ficha[][] getTablero() {
        return tablero;
    }

    public boolean ocuparPosicion(int x, int y, Ficha ficha) {
        if (!verificarEspacio(x, y)) {
            System.out.println("Espacio ocupado");
            return false;
        } else {
            numOcupados++;
            numDesocupados--;
            this.tablero[x][y] = ficha;
            return true;
        }
    }

    public boolean verificarEspacio(int x, int y) {
        return this.tablero[x][y].getColor().equals("gris");
    }

    public void vaciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tablero[i][j] = new Ficha();
            }
        }
    }

    public boolean verificarVictoria(String color) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].getColor().equals(color) &&
                    tablero[i][1].getColor().equals(color) &&
                    tablero[i][2].getColor().equals(color)) {
                return true;
            }
            if (tablero[0][i].getColor().equals(color) &&
                    tablero[1][i].getColor().equals(color) &&
                    tablero[2][i].getColor().equals(color)) {
                return true;
            }
        }
        // Check diagonals
        if (tablero[0][0].getColor().equals(color) &&
                tablero[1][1].getColor().equals(color) &&
                tablero[2][2].getColor().equals(color)) {
            return true;
        }
        if (tablero[0][2].getColor().equals(color) &&
                tablero[1][1].getColor().equals(color) &&
                tablero[2][0].getColor().equals(color)) {
            return true;
        }
        return false;
    }

    public boolean moverFicha(int x, int y, int newX, int newY, String color) {
        if (verificarEspacio(newX, newY) && this.tablero[x][y].getColor().equals(color)) {
            this.tablero[newX][newY] = this.tablero[x][y];
            this.tablero[x][y] = new Ficha();
            return true;
        }
        return false;
    }

    public void display() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("["+tablero[i][j].getColor() + "]");
            }
            System.out.println();
        }
    }
}
