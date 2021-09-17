package Entidades;

public class Posteo 
{
    private String palabra;
    private String nombreDocumento;
    private int frecuencia;

    public Posteo() 
    {
        
    }
    
    public Posteo(String nombreDocumento, int frecuencia)
    {
        this.nombreDocumento = nombreDocumento;
        this.frecuencia = frecuencia;
    }

    public Posteo(String palabra, String nombreDocumento, int frecuencia) 
    {
        this.palabra = palabra;
        this.nombreDocumento = nombreDocumento;
        this.frecuencia = frecuencia;
    }

    public String getPalabra() 
    {
        return this.palabra;
    }

    public void setPalabra(String palabra) 
    {
        this.palabra = palabra;
    }

    public String getNombreDocumento() 
    {
        return this.nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) 
    {
        this.nombreDocumento = nombreDocumento;
    }

    public int getFrecuencia() 
    {
        return this.frecuencia;
    }

    public void setFrecuencia(int frecuencia) 
    {
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() 
    {
        return "POSTEO -> (" + this.palabra + " - " + this.nombreDocumento + " - " + this.frecuencia + ")";
    }
    
    public void incrementarFrecuencia()
    {
        this.frecuencia++;
    }
}
