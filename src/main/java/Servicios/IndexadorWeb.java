package Servicios;

import Gestores.*;
import Soporte.FormatoTiempo;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "IndexadorWeb", urlPatterns = {"/API/IndexadorWeb/cargarDocumentos"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100)
public class IndexadorWeb extends HttpServlet
{
    public static String mensajeOK = "";
    public static String tiempoInsumido = "";
    public static String raiz = "";
    
    @Inject GestorPosteo gestorPosteo;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        String UploadBDD = "C:\\OneDrive - frc.utn.edu.ar\\UTN\\X2021\\DLC\\DLC-SearchEngine\\src\\main\\webapp\\UploadBDD\\";
        String BDD = "C:\\OneDrive - frc.utn.edu.ar\\UTN\\X2021\\DLC\\DLC-SearchEngine\\src\\main\\webapp\\BDD\\";
        
        boolean okUploadBDD = false, okBDD = false;
        long tiempoInicial = 0, tiempoFinal = 0;
        
        this.borrarArchivos(new File(UploadBDD));
        
        okUploadBDD = this.cargarDocumentos(request, UploadBDD, false);
        if (okUploadBDD)
        {
            tiempoInicial = System.currentTimeMillis();

            this.gestorPosteo.generarPosteo(UploadBDD);
            this.actualizarVocabulario();
            
            tiempoFinal = System.currentTimeMillis();
        
            this.borrarArchivos(new File(UploadBDD));
        
            okBDD = this.cargarDocumentos(request, BDD, true);
        }
        
        if (okUploadBDD && okBDD)
        {
            String tiempoPosteo = FormatoTiempo.obtenerTiempo(tiempoInicial, tiempoFinal);
            
            IndexadorWeb.mensajeOK = "INDEXADO DE POSTEO FINALIZADO";
            IndexadorWeb.tiempoInsumido = "TIEMPO INSUMIDO: " + tiempoPosteo;
            IndexadorWeb.raiz = request.getContextPath();
            
            try 
            {
                response.sendRedirect(request.getContextPath() + "/Interfaz/InformeWeb.jsp");
            } 
            catch (IOException ex) 
            {
                System.out.println("ERROR: NO ES POSIBLE REDIRECCIONAR A \"InformeWeb.jsp\"");
            }
        }
    }
    
    public void borrarArchivos(File carpeta)
    {
        String[] archivosContenidos = carpeta.list();
        
        if (archivosContenidos != null && archivosContenidos.length > 0)
        {
            for (String archivo : archivosContenidos)
            {
                File archivoExistente = new File(carpeta, archivo);
                archivoExistente.delete();
            }
        }
    }
    
    public boolean cargarDocumentos(HttpServletRequest request, String ruta, boolean payara)
    {
        String RutaBDD = request.getServletContext().getRealPath("/") + "BDD/";
        
        try
        {
            File BDDServidor = new File(RutaBDD);
            File BDDLocal = new File(ruta);
            
            List<Part> partes = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
            
            for (Part parte : partes)
            {
                String nombreArchivo = Paths.get(parte.getSubmittedFileName()).getFileName().toString();
                File archivoLocal = new File(BDDLocal, nombreArchivo);
                File archivoServidor = new File(BDDServidor, nombreArchivo);
                InputStream contenidoArchivo = parte.getInputStream();
                
                if (!archivoLocal.exists())
                {
                    Files.copy(contenidoArchivo, archivoLocal.toPath());
                }
                
                if (payara && !archivoServidor.exists())
                {
                    Files.copy(archivoLocal.toPath(), archivoServidor.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (ServletException | IOException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE CARGAR LOS ARCHIVOS");
            
            return false;
        }
        
        return true;
    }
    
    public void actualizarVocabulario()
    {
        if (GestorPosteo.BDDActualizada)
        {
            GestorVocabulario.cargarVocabulario();
            GestorPosteo.BDDActualizada = false;
        }
    }
}
