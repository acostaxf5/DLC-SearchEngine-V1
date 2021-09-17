package Servicios;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import Entidades.*;
import Gestores.GestorBuscador;
import java.net.*;

@Path("/Buscador")
public class Buscador 
{
    public static String contextPath = "";
    public static String consulta = "";
    public static int cantidad = 0;
    public static List<Documento> respuestaDocumentos = null;
    
    @Inject GestorBuscador gestorBuscador;
    
    @POST
    @Path("/obtenerBusqueda")
    public Response obtenerBusqueda(@FormParam("consulta") String consulta, @FormParam("cantidad") int cantidad, @Context ServletContext sc)
    {
        List<String> consultaLimpia = this.gestorBuscador.limpiarConsulta(consulta);
        List<Vocabulario> consultaPesada = this.gestorBuscador.pesarConsulta(consultaLimpia);
        List<Documento> respuesta = this.gestorBuscador.cargarDocumentos(consultaPesada, cantidad, sc);
        
        Buscador.consulta = consulta;
        Buscador.cantidad = cantidad;
        Buscador.respuestaDocumentos = respuesta;
        Buscador.contextPath = sc.getContextPath();
        
        try 
        {
            return Response.seeOther(new URI("/DLC-SearchEngine/Interfaz/Respuesta.jsp")).build();
        } 
        catch (URISyntaxException ex) 
        {
            System.out.println("ERROR: NO ES POSIBLE REDIRECCIONAR A \"Respuesta.jsp\"");
        }
        
        return Response.status(500, "ERROR EN LA GENERACIÓN DE LA RESPUESTA HACIA LA CONSULTA").build();
    }
}
