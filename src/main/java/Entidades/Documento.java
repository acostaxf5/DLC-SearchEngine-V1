package Entidades;

public class Documento implements Comparable<Documento>
{
    private String nombreDocumento;
    private double indiceRelevancia;

    public Documento() 
    {
        
    }
    
    public Documento(String nombreDocumento)
    {
        this.nombreDocumento = nombreDocumento;
    }

    public Documento(String nombreDocumento, double indiceRelevancia) 
    {
        this.nombreDocumento = nombreDocumento;
        this.indiceRelevancia = indiceRelevancia;
    }

    public String getNombreDocumento() 
    {
        return this.nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) 
    {
        this.nombreDocumento = nombreDocumento;
    }

    public double getIndiceRelevancia() 
    {
        return this.indiceRelevancia;
    }

    public void setIndiceRelevancia(double indiceRelevancia) 
    {
        this.indiceRelevancia = indiceRelevancia;
    }

    @Override
    public String toString() 
    {
        return "DOCUMENTO -> (" + this.nombreDocumento + " - " + this.indiceRelevancia + ")";
    }
    
    @Override
    public int compareTo(Documento documento)
    {
        double iRDA = documento.getIndiceRelevancia();
        double iRDB = this.indiceRelevancia;
        
        double iRR = iRDA - iRDB;
        if (iRR > 0)
        {
            return 1;
        }
        else if (iRR < 0)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
