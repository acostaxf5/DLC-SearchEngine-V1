package Gestores;

import java.io.File;
import java.util.*;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.ServletContext;
import DAO.PosteoDAO;
import Entidades.*;
import Soporte.Calculos;

@ApplicationScoped
public class GestorBuscador 
{
    public List<String> limpiarConsulta(String consulta)
    {
        List<String> consultaLimpia = new ArrayList<>();
        
        for (String palabra : consulta.split(" "))
        {
            StringBuilder palabraLimpia = new StringBuilder();
            for (Character letra : palabra.toCharArray())
            {
                if (Character.isAlphabetic(letra))
                {
                    palabraLimpia.append(letra);
                }
            }
            
            consultaLimpia.add(palabraLimpia.toString().toLowerCase(Locale.ROOT));
        }
        
        return consultaLimpia;
    }
    
    public List<Vocabulario> pesarConsulta(List<String> consultaLimpia)
    {
        List<Vocabulario> consultaPesada = new ArrayList<>();
        
        for (String palabra : consultaLimpia)
        {
            Vocabulario palabraVocabulario = GestorVocabulario.vocabulario.get(palabra);
            if (palabraVocabulario != null)
            {
                consultaPesada.add(palabraVocabulario);
            }
        }
        
        Collections.sort(consultaPesada);
        
        return consultaPesada;
    }
    
    public List<Documento> cargarDocumentos(List<Vocabulario> consultaPesada, int cantidadDocumentos, ServletContext sc)
    {
        String[] listaDocumentos = new File(sc.getRealPath("/") + "BDD/").list();
        int totalDocumentosBDD = listaDocumentos.length;
        
        HashMap<String, Documento> documentos = new HashMap<>();
        
        for (Vocabulario vocabulario : consultaPesada)
        {
            List<Posteo> posteoVocabulario = PosteoDAO.obtenerPosteoVocabulario(vocabulario.getPalabra());
            
            for (Posteo posteo : posteoVocabulario)
            {
                Documento documento = new Documento(posteo.getNombreDocumento());
                int frecuencia = posteo.getFrecuencia();
                int cantidadDocumentosPalabra = vocabulario.getCantidadDocumentos();
                double iR = Calculos.calcularIndiceRelevancia(frecuencia, totalDocumentosBDD, cantidadDocumentosPalabra);
                documento.setIndiceRelevancia(iR);
                
                Documento documentoExistente = documentos.get(posteo.getNombreDocumento());
                if (documentoExistente != null)
                {
                    double relevanciaAnterior = documentoExistente.getIndiceRelevancia();
                    double relevanciaActual = documento.getIndiceRelevancia();
                    double relevanciaNueva = relevanciaAnterior + relevanciaActual;
                    
                    documentoExistente.setIndiceRelevancia(relevanciaNueva);
                }
                else
                {
                    documentos.put(posteo.getNombreDocumento(), documento);
                }
            }
        }
        
        List<Documento> respuestaConsulta = new ArrayList<>();
        
        for (String nombreDocumento : documentos.keySet())
        {
            Documento documento = documentos.get(nombreDocumento);
            if (documento != null)            
            {
                respuestaConsulta.add(documento);
            }
        }
        
        Collections.sort(respuestaConsulta);
        
        return respuestaConsulta;
    }
}
