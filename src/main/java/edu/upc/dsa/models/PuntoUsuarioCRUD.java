package edu.upc.dsa.models;

public class PuntoUsuarioCRUD {
    private String punto;
    private String fecha;

    public PuntoUsuarioCRUD(){}
    public PuntoUsuarioCRUD(String punto,String fecha){
        this();
        this.punto=punto;
        this.fecha=fecha;
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
