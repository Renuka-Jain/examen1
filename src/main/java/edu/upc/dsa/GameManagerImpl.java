package edu.upc.dsa;

import edu.upc.dsa.models.Punto;
import edu.upc.dsa.models.PuntoUsuario;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class GameManagerImpl implements GameManager{

    private static GameManagerImpl manager;
    private HashMap<String, Usuario> gameUsers;
    private List<Punto> puntos;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);



    private GameManagerImpl(){
        this.gameUsers = new HashMap<>();
        this.puntos = new LinkedList<Punto>();}

    public static GameManagerImpl getInstance(){
        if(manager==null){
            logger.info("New instance edu.upc.dsa.GameManagerImpl");
            manager = new GameManagerImpl();
        }
        return manager;
    }

    public static void delete(){
        manager = null;
        logger.info("Instance GameManagerImpl deleted");
    }

    public void clear(){
        gameUsers.clear();
        puntos.clear();
    }

    @Override
    public List<Usuario> listarUsuariosOrdenados() {
        logger.info("sort users alphabetically by name");
        List<Usuario> usuariosOrdenados = Arrays.asList(gameUsers.values().stream().sorted(
                (s1,s2)->s1.getNombre().compareToIgnoreCase(s2.getNombre())).toArray(Usuario[]::new));

        return usuariosOrdenados;
    }

    @Override
    public Usuario addUsuario(String nombreUsuario) {
        return this.addUsuario(new Usuario(nombreUsuario));
    }
    public Usuario addUsuario(Usuario user) {
        logger.info("new user " + user);
        this.gameUsers.put(user.getId(), user);
        logger.info("new user added");
        return user;
    }


    @Override
    public int numUsuario() {
        int ret = this.gameUsers.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public Usuario consultarInfoUsuario(String id) {
        logger.info("get user info ("+id+")");
        Usuario user = this.gameUsers.get(id);
        if(user!=null){
            logger.info("get user ("+id+"): "+user.getNombre());
            return user;
        }

        logger.warn("not found " + id);
        return null;
    }

    @Override
    public Integer addPuntoUsuario(String id, String punto,String fecha) {
        Usuario usuario = this.gameUsers.get(id);

        logger.info("Nombre usuario addPunto: "+usuario.getNombre());

        for(Punto obj: manager.puntos){
            if(obj.getNombre().equals(punto)){
                logger.info("punto: "+obj.getNombre());
                usuario.addPuntoLista(obj,fecha);
                logger.info("punto añadido");
                return 0;
            }
        }

        logger.warn("user or punto not found");
        return -1;
    }

    @Override
    public List<PuntoUsuario> listadoPuntosUsuario(String id) {
        List<PuntoUsuario> userPuntos = this.gameUsers.get(id).listPuntoFecha();
        if(userPuntos.size()!=0)
            logger.info("User "+id+" "+userPuntos);
        else
            logger.warn("user or punto not found");

        return userPuntos;
    }

    @Override
    public int numObjetosUsuario(String id) {
        int res = this.gameUsers.get(id).getListaPuntos().size();
        if(res != 0)
            logger.info("UserID: "+id+" -> Number of punto: "+res);
        else
            logger.warn("User not found");
        return res;
    }

    @Override
    public List<Usuario>listadoUsuariosPasan(String punto){
        ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
        logger.info("Listar usuarios que han pasado por: "+punto);
        for(Usuario user: gameUsers.values()){
            if(user.pasaPunto(punto)){
                usuarios.add(user);
            }
        }
        return usuarios;
    }

    @Override
    public List<Usuario>listarUsuariosPuntos(){
        logger.info("Listar usuarios por orden de puntos");
        ArrayList<Usuario> usuarioPuntos = new ArrayList<Usuario>(gameUsers.values());
        Collections.sort(usuarioPuntos,
                (Usuario o1, Usuario o2)->Integer.compare(o2.numPuntos(), o1.numPuntos()));
        return usuarioPuntos;
    }

    @Override
    public void addPunto (String nombre){
        puntos.add(new Punto(nombre));
        logger.info("add punto "+nombre);
    }

    @Override
    public String getUsuarioID(String nombreUsuario){
        String idUsuario= this.gameUsers.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue().getNombre(), nombreUsuario))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()).get(0);
        if (idUsuario!=null){
            logger.info("Usuario ("+nombreUsuario+") ID: "+idUsuario);
            return idUsuario;
        }
        logger.warn("User not found");
        return null;

    }
    public Usuario getUsuario(String id){
        return gameUsers.get(id);

    }
    public Punto getPunto(String nombre){
        logger.info("punto: "+nombre+" encontrado: "+ puntos.stream().filter(n->n.getNombre()==nombre).collect(Collectors.toList()).get(0).getNombre());
        return puntos.stream().filter(n->n.getNombre()==nombre).collect(Collectors.toList()).get(0);
    }

    public int numPunto(){
        return puntos.size();
    }
}
