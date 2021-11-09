package edu.upc.dsa.models;

public class Punto {
    private String nombre;

    public Punto() {}

    public Punto(String nombre) {
        this();
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
