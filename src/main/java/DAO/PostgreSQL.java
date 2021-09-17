package DAO;

import java.sql.*;

public class PostgreSQL 
{
    private static PostgreSQL instancia;
    private final String driver = "org.postgresql.Driver";
    private final String usuario = "postgres";
    private final String clave = "UTN-FRC";
    private final String url = "jdbc:postgresql://localhost:5432/dlc-searchengine";
    private String propiedades = "";
    private Connection conexion;
    
    public static PostgreSQL obtenerInstancia()
    {
        if (PostgreSQL.instancia == null)
        {
            PostgreSQL.instancia = new PostgreSQL();
        }
        
        return PostgreSQL.instancia;
    }
    
    public PostgreSQL()
    {
        
    }
    
    public void configurarPropiedades(String propiedades)
    {
        this.propiedades = propiedades;
    }
    
    public Connection abrirConexion()
    {
        try
        {
            if (this.propiedades.equals(""))
            {
                this.conexion = DriverManager.getConnection(this.url, this.usuario, this.clave);
            }
            else
            {
                this.conexion = DriverManager.getConnection(this.url + "?" + this.propiedades, this.usuario, this.clave);
            }
            
            Class.forName(this.driver);
        }
        catch (SQLException | ClassNotFoundException ex)
        {
            System.out.println("ERROR: NO ES POSIBLE ABRIR UNA CONEXIÓN CON LA BASE DE DATOS");
        }
        
        return this.conexion;
    }
    
    public void cerrarConexion()
    {
        if (this.conexion != null)
        {
            try
            {
                this.conexion.close();
            }
            catch (SQLException ex)
            {
                System.out.println("ERROR: NO ES POSIBLE CERRAR LA CONEXIÓN CON LA BASE DE DATOS");
            }
        }
    }
}
