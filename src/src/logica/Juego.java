package logica;

import java.util.Scanner;

public class Juego {
    private Tablero tab;
    private Jugador jugador1;
    private Jugador jugador2;
    private int numTurnos;
    private boolean gameWon;

    public Juego() {
        this.tab = new Tablero();
        this.jugador1 = new Jugador("rojo");
        this.jugador2 = new Jugador("azul");
        this.numTurnos = 0;
        this.gameWon = false;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        Jugador currentPlayer = jugador1;

        while (!gameWon && numTurnos < 6) {
            System.out.println("Jugador " + currentPlayer.getColor() + ", ingresa la posición para colocar tu ficha (x y): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            boolean success = currentPlayer.ponerFicha(tab, x, y);
            if (success) {
                numTurnos++;
                tab.display();  // Display the board after each move
                if (tab.verificarVictoria(currentPlayer.getColor())) {
                    System.out.println("Jugador " + currentPlayer.getColor() + " ha ganado!");
                    gameWon = true;
                } else {
                    currentPlayer = (currentPlayer == jugador1) ? jugador2 : jugador1;
                }
            } else {
                System.out.println("Movimiento inválido, intenta de nuevo.");
            }
        }

        while (!gameWon) {
            System.out.println("Fase de movimiento. Jugador " + currentPlayer.getColor() + ", ingresa la posición de la ficha que quieres mover (x y) y la nueva posición (newX newY): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int newX = scanner.nextInt();
            int newY = scanner.nextInt();
            boolean success = currentPlayer.moverFicha(tab, x, y, newX, newY);
            if (success) {
                tab.display();  // Display the board after each move
                if (tab.verificarVictoria(currentPlayer.getColor())) {
                    System.out.println("Jugador " + currentPlayer.getColor() + " ha ganado!");
                    gameWon = true;
                } else {
                    currentPlayer = (currentPlayer == jugador1) ? jugador2 : jugador1;
                }
            } else {
                System.out.println("Movimiento inválido, intenta de nuevo.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
}
