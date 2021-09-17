package Soporte;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class FormatoTiempo 
{
    public static String obtenerTiempo(long inicio, long fin)
    {
        long diferencia = fin - inicio;
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        return sdf.format(diferencia);
    }
}
