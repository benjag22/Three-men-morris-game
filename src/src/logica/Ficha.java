package logica;

public class Ficha {
    private String color;

    public Ficha(String color) {
        this.color = color;
    }
    public Ficha() {
        this.color = "gris";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
