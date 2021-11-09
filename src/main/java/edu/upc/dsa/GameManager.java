package edu.upc.dsa;


import edu.upc.dsa.models.Punto;
import edu.upc.dsa.models.PuntoUsuario;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface GameManager {
    public List<Usuario> listarUsuariosOrdenados();
    public Usuario addUsuario(String nombreUsuario);
    public Usuario addUsuario(Usuario usuario);
    public int numUsuario();
    public Usuario consultarInfoUsuario(String id);
    public Integer addPuntoUsuario(String id,String punto,String fecha);
    public List<PuntoUsuario> listadoPuntosUsuario(String id);//Lista de puntos usuario
    public List<Usuario>listadoUsuariosPasan(String punto);
    public List<Usuario>listarUsuariosPuntos();//Lista usuarios por puntos
    public int numObjetosUsuario(String nombreUsuario);

    public void addPunto(String nombre);
    public String getUsuarioID(String nombre);
    public int numPunto();
    public Usuario getUsuario(String id);
    public Punto getPunto(String nombre);
}
