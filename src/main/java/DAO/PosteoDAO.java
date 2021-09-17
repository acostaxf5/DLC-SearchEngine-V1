package DAO;

import java.util.*;
import java.sql.*;
import Entidades.*;

public class PosteoDAO
{
    public static void insertarTablaPosteos(HashMap<String, Posteo> tablaPosteo)
    {
        String sql = "";
        
        sql += "INSERT INTO public.posteos ";
        sql += "VALUES(?, ?, ?)";
        
        PostgreSQL dao = PostgreSQL.obtenerInstancia();
        dao.configurarPropiedades("reWriteBatchedInserts=true");
        
        Connection conexion = dao.abrirConexion();
        
        try (PreparedStatement ps = conexion.prepareStatement(sql))
        {
            conexion.setAutoCommit(false);
            
            long contador = 0;
            for (String palabra : tablaPosteo.keySet())
            {
                contador++;
                
                Posteo posteo = tablaPosteo.get(palabra);
                
                ps.setString(1, posteo.getPalabra());
                ps.setString(2, posteo.getNombreDocumento());
                ps.setInt(3, posteo.getFrecuencia());
                
                ps.addBatch();
                
                if (contador % 1000 == 0 || contador == tablaPosteo.size())
                {
                    ps.executeBatch();
                }
            }
            
            ps.close();
            
            conexion.commit();
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE ESCRIBIR EN LA BASE DE DATOS");
        }
        finally
        {
            dao.cerrarConexion();
        }
    }
    
    public static HashMap<String, Vocabulario> obtenerPalabrasExistentes()
    {
        String sql = "";
        
        sql += "SELECT palabra, COUNT(\"nombreDocumento\") as cantidadDocumentos, MAX(frecuencia) as frecuencia ";
        sql += "FROM public.posteos ";
        sql += "GROUP BY palabra ";
        sql += "ORDER BY frecuencia DESC";
        
        HashMap<String, Vocabulario> vocabulario = new HashMap<>();
        
        PostgreSQL dao = PostgreSQL.obtenerInstancia();
        
        Connection conexion = dao.abrirConexion();

        try (PreparedStatement ps = conexion.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                String palabra = rs.getString("palabra");
                int cantidadDocumentos = rs.getInt("cantidadDocumentos");
                int frecuenciaMaxima = rs.getInt("frecuencia");
                
                Vocabulario nuevaPalabra = new Vocabulario(palabra, cantidadDocumentos, frecuenciaMaxima);
                
                vocabulario.put(palabra, nuevaPalabra);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE LEER LA BASE DE DATOS");
        }
        finally
        {
            dao.cerrarConexion();
        }
        
        return vocabulario;
    }
    
    public static List<Posteo> obtenerPosteoVocabulario(String palabra)
    {
        String sql = "";
        
        sql += "SELECT \"nombreDocumento\", frecuencia ";
        sql += "FROM public.posteos ";
        sql += "WHERE palabra='" + palabra + "' ";
        sql += "ORDER BY frecuencia DESC";
        
        PostgreSQL dao = PostgreSQL.obtenerInstancia();
        
        Connection conexion = dao.abrirConexion();
        
        List<Posteo> posteo = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next())
            {
                String nombreDocumento = rs.getString("nombreDocumento");
                int frecuencia = rs.getInt("frecuencia");
                
                Posteo posteoGenerado = new Posteo(nombreDocumento, frecuencia);
                
                posteo.add(posteoGenerado);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE LEER LA BASE DE DATOS");
        }
        
        return posteo;
    }
    
    public static boolean existeDocumento(String documento)
    {
        String sql = "";
        
        sql += "SELECT \"nombreDocumento\" ";
        sql += "FROM public.posteos ";
        sql += "WHERE \"nombreDocumento\"='" + documento + "' ";
        sql += "GROUP BY \"nombreDocumento\"";
        
        PostgreSQL dao = PostgreSQL.obtenerInstancia();
        
        Connection conexion = dao.abrirConexion();
        
        boolean existe = false;
        
        try (PreparedStatement ps = conexion.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                existe = true;
            }
        }
        catch (SQLException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE LEER LA BASE DE DATOS");
            System.out.println(ex);
        }
        finally
        {
            dao.cerrarConexion();
        }
        
        return existe;
    }
}
