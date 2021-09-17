package Entidades;

public class Vocabulario implements Comparable<Vocabulario>
{
    private String palabra;
    private int cantidadDocumentos;
    private int frecuenciaMaxima;

    public Vocabulario() 
    {
        
    }

    public Vocabulario(String palabra, int cantidadDocumentos, int frecuenciaMaxima) 
    {
        this.palabra = palabra;
        this.cantidadDocumentos = cantidadDocumentos;
        this.frecuenciaMaxima = frecuenciaMaxima;
    }

    public String getPalabra() 
    {
        return this.palabra;
    }

    public void setPalabra(String palabra) 
    {
        this.palabra = palabra;
    }

    public int getCantidadDocumentos() 
    {
        return this.cantidadDocumentos;
    }

    public void setCantidadDocumentos(int cantidadDocumentos) 
    {
        this.cantidadDocumentos = cantidadDocumentos;
    }

    public int getFrecuenciaMaxima() 
    {
        return this.frecuenciaMaxima;
    }

    public void setFrecuenciaMaxima(int frecuenciaMaxima) 
    {
        this.frecuenciaMaxima = frecuenciaMaxima;
    }

    @Override
    public String toString() {
        return "VOCABULARIO -> (" + this.palabra + " - " + this.cantidadDocumentos + " - " + this.frecuenciaMaxima + ")";
    }
    
    @Override
    public int compareTo(Vocabulario vocabulario)
    {
        return this.cantidadDocumentos - vocabulario.getCantidadDocumentos();
    }
}
