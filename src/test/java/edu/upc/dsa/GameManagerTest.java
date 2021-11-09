package edu.upc.dsa;

import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GameManagerTest {
    @After
    public void tearDown() {
        GameManagerImpl.getInstance().clear();
        GameManagerImpl.delete();
    }


    @Before
    public void setUp() {
        //Añadir puntos
        GameManagerImpl.getInstance().addPunto("Puerta");
        GameManagerImpl.getInstance().addPunto("Casilla");

        //Crear Usuarios
        GameManagerImpl.getInstance().addUsuario("Lorena");
        GameManagerImpl.getInstance().addUsuario("Julia");
        GameManagerImpl.getInstance().addUsuario("Paco");
    }

    @Test
    public void testListarUsuariosOrdenados(){
        Assert.assertEquals("Julia",GameManagerImpl.getInstance().listarUsuariosOrdenados().get(0).getNombre());

    }

    @Test
    public void testAddUsuario(){

        Assert.assertEquals(3,GameManagerImpl.getInstance().numUsuario());
        GameManagerImpl.getInstance().addUsuario("Eulalia");
        Assert.assertEquals(4,GameManagerImpl.getInstance().numUsuario());
    }

    @Test
    public void testInfoUsuario(){
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Puerta","2021-06-28 10:15");
        Assert.assertEquals("Julia",GameManagerImpl.getInstance().consultarInfoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia"))).getNombre());
        Assert.assertEquals( 1 ,GameManagerImpl.getInstance().consultarInfoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia"))).numPuntos());

    }

    @Test
    public void testAddPuntoUsuario(){
        Assert.assertEquals(0,GameManagerImpl.getInstance().listadoPuntosUsuario(GameManagerImpl.getInstance().getUsuarioID("Julia")).size());
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Puerta","2021-06-28 10:15");

        Assert.assertEquals(1,GameManagerImpl.getInstance().listadoPuntosUsuario(GameManagerImpl.getInstance().getUsuarioID("Julia")).size());
    }
    @Test
    public void testListadoPuntosUsuario(){
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Casilla","2021-06-28 10:15");
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Puerta","2021-06-25 11:14");

        Assert.assertEquals("Puerta",GameManagerImpl.getInstance().listadoPuntosUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia"))).get(0).getPunto().getNombre());
        Assert.assertEquals( "Casilla" ,GameManagerImpl.getInstance().listadoPuntosUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia"))).get(1).getPunto().getNombre());
    }

    @Test
    public void testListadoUsuariosPasan(){
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Paco")),"Casilla","2021-06-28 10:15");
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Puerta","2021-06-28 10:15");

        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Lorena")),"Puerta","2021-06-28 10:15");

        Assert.assertEquals(2,GameManagerImpl.getInstance().listadoUsuariosPasan("Puerta").size());
    }

    @Test
    public void testListarUsuariosPuntos(){
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Casilla","2021-06-28 10:15");
        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Julia")),"Puerta","2021-06-28 10:15");

        GameManagerImpl.getInstance().addPuntoUsuario((GameManagerImpl.getInstance().getUsuarioID("Lorena")),"Puerta","2021-06-28 10:15");

        Assert.assertEquals(2,GameManagerImpl.getInstance().listarUsuariosPuntos().get(0).numPuntos());
        Assert.assertEquals(1,GameManagerImpl.getInstance().listarUsuariosPuntos().get(1).numPuntos());
        Assert.assertEquals(0,GameManagerImpl.getInstance().listarUsuariosPuntos().get(2).numPuntos());
    }



}
