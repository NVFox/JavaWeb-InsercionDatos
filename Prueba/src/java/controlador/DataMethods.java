package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;
import org.apache.tomcat.util.http.*;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class DataMethods {

    public ArrayList<String[]> recolectarDatos(String[] campos, HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String[]> totales = new ArrayList<>();
        ArrayList<String> camposTotales = new ArrayList<>();
        ArrayList<String> valores = new ArrayList<>();

        for (int i = 0; i < campos.length; i++) {
            String item;
            
            if (campos[i].matches("(?i)imagen")) {
                item = recolectarImagen(request, campos[i], request.getParameter(campos[0]));
            } else {
                item = request.getParameter(campos[i]);
            }

            if (item != null && !item.equals("")) {
                camposTotales.add(campos[i]);
                valores.add(item);
            }
        }

        totales.add(camposTotales.toArray(new String[0]));
        totales.add(valores.toArray(new String[0]));

        return totales;
    }
    
    public String recolectarImagen(HttpServletRequest request, String campoImagen, String id) {
        try {
            Part imagenArchivo = request.getPart(campoImagen);
            String nombreArchivo = id + imagenArchivo.getSubmittedFileName();
            
            InputStream file = imagenArchivo.getInputStream();
            File archivoFinal = new File("C:\\Users\\usuario\\Documents\\NetBeansProjects\\Prueba\\web\\imagenes\\" + nombreArchivo);
            FileOutputStream salidaArchivo = new FileOutputStream(archivoFinal);
            int num = file.read();
            
            while (num != -1) {
                salidaArchivo.write(num);
                num = file.read();
            }
            
            return "imagenes/" + nombreArchivo;
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fallo en imagen " + ex);
            return null;
        }
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
