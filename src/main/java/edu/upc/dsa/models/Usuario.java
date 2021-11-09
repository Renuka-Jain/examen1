package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombre;
    private List<PuntoUsuario> listaPuntos = new LinkedList<>();

    public Usuario() {
        this.id = RandomUtils.getId();
    }

    public Usuario(String nombre) {
        this();
        this.setNombre(nombre);
    }
    public Usuario(String id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    public void addPuntoLista(Punto punto,String fecha){
        listaPuntos.add(new PuntoUsuario(punto,fecha));
    }

    public List<Punto> listPuntoFecha(){
        Collections.sort(listaPuntos);
        List<Punto> listaOrdenada = new LinkedList<Punto>();
        for (PuntoUsuario obj: this.listaPuntos) {
            listaOrdenada.add(obj.getPunto());
        }
        return listaOrdenada;
    }

    public boolean pasaPunto(String punto){
        for(PuntoUsuario point:this.listaPuntos){
            if(point.getPunto().getNombre().equals(punto))
                return true;
        }
        return false;
    }

    public int numPuntos(){
        return listaPuntos.size();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PuntoUsuario> getListaPuntos() {
        return listaPuntos;
    }
    public void setListaPuntos(List<PuntoUsuario> listaPuntos) {
        this.listaPuntos = listaPuntos;
    }
}
