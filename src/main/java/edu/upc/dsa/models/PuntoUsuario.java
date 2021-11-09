package edu.upc.dsa.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PuntoUsuario implements Comparable<PuntoUsuario> {
    private Punto punto;
    private LocalDateTime fecha;

    PuntoUsuario(){}

    PuntoUsuario(Punto punto,String fecha){
        this();
        this.punto=punto;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        this.fecha=dateTime;
    }

    public int compareTo(PuntoUsuario g){
        return Math.toIntExact(ChronoUnit.MINUTES.between(g.getFecha(),this.fecha));

    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public Punto getPunto(){
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }
}
