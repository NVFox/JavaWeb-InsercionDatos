package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class DataMethods {

    public ArrayList<String[]> recolectarDatos(String[] campos, HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String[]> totales = new ArrayList<>();
        ArrayList<String> camposTotales = new ArrayList<>();
        ArrayList<String> valores = new ArrayList<>();

        for (int i = 0; i < campos.length; i++) {
            String item = request.getParameter(campos[i]);

            if (item != null && !item.equals("")) {
                camposTotales.add(campos[i]);
                valores.add(item);
            }
        }

        totales.add(camposTotales.toArray(new String[0]));
        totales.add(valores.toArray(new String[0]));

        return totales;
    }
    
    public String[] conseguirCamposFinales(ArrayList<String> campos) {
        campos.remove(campos.size() - 1);
        String[] camposFinales = campos.toArray(new String[0]);
        
        return camposFinales;
    }

    public ArrayList<String> recolectarNombres(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> parameterNames = new ArrayList<>();
        Enumeration enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            parameterNames.add(parameterName);
        }

        return parameterNames;
    }
}
