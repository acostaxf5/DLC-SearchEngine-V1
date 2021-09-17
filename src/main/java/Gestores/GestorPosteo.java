package Gestores;

import java.io.*;
import java.util.*;
import javax.enterprise.context.ApplicationScoped;
import DAO.PosteoDAO;
import Entidades.Posteo;

@ApplicationScoped
public class GestorPosteo 
{
    public static boolean BDDActualizada = false;
    
    public List<Integer> generarPosteo(String BDD)
    {
        List<Integer> resultados = new ArrayList<>();
        
        String[] listaDocumentos = new File(BDD).list();
        
        int cantidadDocumentos = 0;
        
        assert listaDocumentos != null;
        for (String documento : listaDocumentos)
        {
            cantidadDocumentos++;
            System.out.println("\t- Documento " + cantidadDocumentos + "/" + listaDocumentos.length + ": \"" + documento + "\"");
            
            HashMap<String, Posteo> tablaDocumento = new HashMap<>();
            
            boolean existeDocumentoIndexado = PosteoDAO.existeDocumento(documento);
            if (!existeDocumentoIndexado)
            {
                GestorPosteo.BDDActualizada = true;
                
                try
                {
                    FileReader fileReader = new FileReader(BDD + documento);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    Scanner scanner = new Scanner(bufferedReader);
                    scanner.useDelimiter("[^a-zA-Z \\-]|( )|(^\\s*$)|\\-");
                    
                    while (scanner.hasNext())
                    {
                        String palabraActual = scanner.next().toLowerCase(Locale.ROOT);
                        if (palabraActual.length() > 0 && 'a' <= palabraActual.charAt(0) && 'z' >= palabraActual.charAt(0))
                        {
                            Posteo posteoExistente = tablaDocumento.get(palabraActual);
                            if (posteoExistente == null)
                            {
                                tablaDocumento.put(palabraActual, new Posteo(palabraActual, documento, 1));
                            }
                            else
                            {
                                posteoExistente.incrementarFrecuencia();
                            }
                        }
                    }
                }
                catch (IOException ex)
                {
                    System.out.println("ERROR: NO ES POSIBLE ACCEDER AL DOCUMENTO");
                }
                
                PosteoDAO.insertarTablaPosteos(tablaDocumento);
            }
        }
        
        resultados.add(listaDocumentos.length);
        resultados.add(cantidadDocumentos);
        
        return resultados;
    }
}
