package Soporte;

public class Calculos 
{
    public static double calcularIndiceRelevancia(float frecuenciaTermino, float totalDocumentos, float documentos)
    {
        return frecuenciaTermino * (Math.log10(totalDocumentos/documentos));
    }
}
