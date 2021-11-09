package edu.upc.dsa.services;

import edu.upc.dsa.models.*;
import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {

    private GameManager manager;

    public GameService() {
        this.manager = GameManagerImpl.getInstance();

        if (manager.numPunto()==0) {
            //Añadir objetos disponibles
            GameManagerImpl.getInstance().addPunto("Puerta");
            GameManagerImpl.getInstance().addPunto("Casilla");
        }
        if(manager.numUsuario()==0) {

            //Crear clientes
            GameManagerImpl.getInstance().addUsuario("Lorena");
            GameManagerImpl.getInstance().addUsuario("Julia");
            GameManagerImpl.getInstance().addUsuario("Paco");
        }

    }

    @GET
    @ApiOperation(value = "Listado de usuarios ordenado alfabéticamente", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Lista de usuarios no encontrada (está vacía)")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuariosOrdenados(){

        List<Usuario> usuarioList = this.manager.listarUsuariosOrdenados();
        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarioList) {};

        if(usuarioList.size() > 0)
            return Response.status(201).entity(entity).build();
        return Response.status(404).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Añadir un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(UsuarioCRUD user) {
        if (user.getNombre()==null )  return Response.status(500).entity(user).build();
        this.manager.addUsuario(new Usuario(user.getNombre()));
        return Response.status(201).entity(user).build();
    }

    @GET
    @ApiOperation(value = "Consultar información de un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuario.class),
            @ApiResponse(code = 404, message = "Usuario not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInfoUsuario(@PathParam("id") String id) {
        Usuario user = this.manager.consultarInfoUsuario(id);
        if (user == null) return Response.status(404).build();
        else  return Response.status(201).entity(user).build();
    }


    @GET
    @ApiOperation(value = "Consultar los puntos de interés por los que un usuario pasa", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Punto.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Lista de objetos no encontrada (está vacía)")
    })


    @Path("/getPuntos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoObjetosUsuario(@PathParam("id") String id){

        List<Punto> objetoList = this.manager.listadoPuntosUsuario(id);
        GenericEntity<List<Punto>> entity = new GenericEntity<List<Punto>>(objetoList) {};

        if(objetoList.size() > 0)
            return Response.status(201).entity(entity).build();
        else
            return Response.status(404).entity(entity).build();
    }


    @PUT
    @ApiOperation(value = "Informar que un usuario pasa por un punto de interés", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Integer.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/AddObjeto/{idUser}")
    public Response addPuntoUsuario(@PathParam("idUser") String idUser, PuntoUsuarioCRUD pUser) {

        Integer res=this.manager.addPuntoUsuario(idUser,pUser.getPunto(),pUser.getFecha());

        if (res!=0) return Response.status(404).build();
        return Response.status(201).build();
    }


    @GET
    @ApiOperation(value = "Listado de usuarios que han pasado por un punto de interés", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UsuarioCRUD.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Lista de objetos no encontrada (está vacía)")
    })


    @Path("/getUsuarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoUsuariosPasan(@PathParam("id") String id){

        List<Usuario> usuarioList = this.manager.listadoUsuariosPasan(id);
        List<UsuarioCRUD> usuarioList2= new LinkedList<>();
        for(Usuario user:usuarioList){
            usuarioList2.add(new UsuarioCRUD(user.getNombre()));
        }
        GenericEntity<List<UsuarioCRUD>> entity = new GenericEntity<List<UsuarioCRUD>>(usuarioList2) {};

        if(usuarioList.size() > 0)
            return Response.status(201).entity(entity).build();
        else
            return Response.status(404).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Listado de usuarios ordenado descendentemente por puntos de interés por los que han pasado", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UsuarioCRUD.class, responseContainer="List"),
            @ApiResponse(code = 404, message= "Lista de objetos no encontrada (está vacía)")
    })


    @Path("/getUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listadoUsuariosPorPuntos(){

        List<Usuario> usuarioList = this.manager.listarUsuariosPuntos();
        List<UsuarioCRUD> usuarioList2= new LinkedList<>();
        for(Usuario user:usuarioList){
            usuarioList2.add(new UsuarioCRUD(user.getNombre()));
        }
        GenericEntity<List<UsuarioCRUD>> entity = new GenericEntity<List<UsuarioCRUD>>(usuarioList2) {};

        if(usuarioList.size() > 0)
            return Response.status(201).entity(entity).build();
        else
            return Response.status(404).entity(entity).build();
    }







}