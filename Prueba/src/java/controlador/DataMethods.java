package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class DataMethods {
    public String[] recolectarDatos(String btnNombre, String[] campos, HttpServletRequest request, HttpServletResponse response) {
        String[] valores = new String[campos.length];

        if (request.getParameter(btnNombre) != null) {
            for (int i = 0; i < campos.length; i++){
                valores[i] = request.getParameter(campos[i]);
            }
        }
        
        JOptionPane.showMessageDialog(null, Arrays.toString(valores));
        
        return valores;
    }
    
    public ArrayList<String> recolectarNombres(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> parameterNames = new ArrayList<>();
        Enumeration enumeration = request.getParameterNames();
        
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            JOptionPane.showMessageDialog(null, parameterName);
            parameterNames.add(parameterName);
        }
        
        return parameterNames;
    }
}
