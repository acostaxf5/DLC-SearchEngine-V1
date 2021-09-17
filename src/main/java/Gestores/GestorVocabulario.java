package Gestores;

import java.util.HashMap;
import DAO.PosteoDAO;
import Entidades.Vocabulario;

public class GestorVocabulario 
{
    public static HashMap<String, Vocabulario> vocabulario;
    
    public static void cargarVocabulario()
    {
        GestorVocabulario.vocabulario = PosteoDAO.obtenerPalabrasExistentes();
    }
}
