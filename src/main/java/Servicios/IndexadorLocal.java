package Servicios;

import Gestores.*;
import Soporte.FormatoTiempo;
import java.net.*;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/IndexadorLocal")
public class IndexadorLocal
{
    public static String mensajeOK = "";
    public static String tiempoInsumido = "";
    public static String contextPath = "";
    
    @Inject GestorPosteo gestorPosteo;
    
    @GET
    @Path("/iniciarIndexacion")
    public Response iniciarIndexacion(@Context ServletContext sc)
    {
        String BDD = sc.getRealPath("/") + "BDD/";
        
        long tiempoInicialPosteo = System.currentTimeMillis();
        List<Integer> resultadoIndexacion = this.gestorPosteo.generarPosteo(BDD);
        long tiempoFinalPosteo = System.currentTimeMillis();
        
        int cantidadDocumentos = resultadoIndexacion.get(0);
        int cantidadDocumentosProcesados = resultadoIndexacion.get(1);
        
        if (cantidadDocumentos == cantidadDocumentosProcesados)
        {
            String tiempoPosteo = FormatoTiempo.obtenerTiempo(tiempoInicialPosteo, tiempoFinalPosteo);
            
            IndexadorLocal.mensajeOK = "INDEXADO DE POSTEO FINALIZADO";
            IndexadorLocal.tiempoInsumido = "TIEMPO INSUMIDO: " + tiempoPosteo;
            IndexadorLocal.contextPath = sc.getContextPath();
            
            GestorVocabulario.cargarVocabulario();
            
            try 
            {
                return Response.seeOther(new URI("/DLC-SearchEngine/Interfaz/InformeLocal.jsp")).build();
            } 
            catch (URISyntaxException ex) 
            {
                System.out.println("ERROR: NO ES POSIBLE REDIRECCIONAR A \"InformeLocal.jsp\"");
            }
        }
        
        return Response.status(500, "ERROR EN LA GENERACIÓN DEL POSTEO").build();
    }
}
