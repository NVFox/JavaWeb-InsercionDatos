/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import modelo.DatosDAO;
import modelo.Tabla;

/**
 *
 * @author SENA
 */
@WebServlet(name = "ServletDatos", urlPatterns = {"/ServletDatos"})
public class ServletDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DataMethods data = new DataMethods();

        ArrayList<String> campos = data.recolectarNombres(request, response);
        String nombreOriginal = campos.get(campos.size() - 1);
        String nombreTabla = nombreOriginal.replaceAll("btn-(\\w+)-", "");
        
        String[] camposFinales = data.conseguirCamposFinales(campos);
        DatosDAO dao = new DatosDAO();

        if (request.getParameter("btn-main-" + nombreTabla) != null) {
            String[] valores = data.recolectarDatos(camposFinales, request, response).get(1);

            Tabla tabla = new Tabla(nombreTabla, camposFinales, valores);
            
            if (dao.insertarDato(tabla.getNombreTabla(), tabla.getValoresTabla())) {
                JOptionPane.showMessageDialog(null, "Datos Insertados");
            }
        } else if (request.getParameter("btn-update-" + nombreTabla) != null) {
            ArrayList<String[]> valores = data.recolectarDatos(camposFinales, request, response);
            camposFinales = valores.get(0);
            String[] valoresFinales = valores.get(1);

            Tabla tabla = new Tabla(nombreTabla, camposFinales, valoresFinales);
            
            if (dao.actualizarDato(tabla.getNombreTabla(), tabla.getCamposTabla(), tabla.getValoresTabla())) {
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
            }
        } else if (request.getParameter("btn-delete-" + nombreTabla) != null) {
            String campoPrincipal = camposFinales[0];
            String codigo = request.getParameter(campoPrincipal);
            
            if (dao.borrarDato(nombreTabla, campoPrincipal, codigo)) {
                JOptionPane.showMessageDialog(null, "Datos Eliminados");
            }
        }
        
        if (nombreTabla.equals("usuarios")) {
            response.sendRedirect("http://localhost:8080/Prueba");
        } else {
            response.sendRedirect(nombreTabla);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
