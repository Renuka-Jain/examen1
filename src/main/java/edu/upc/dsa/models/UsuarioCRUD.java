package edu.upc.dsa.models;

public class UsuarioCRUD {
    private String nombre;

    public UsuarioCRUD(){    }
    public UsuarioCRUD(String nombre) {
        this();
        this.setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
