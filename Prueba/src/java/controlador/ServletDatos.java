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
        String nombreTabla = nombreOriginal.replace("btn-main-", "");
        
        campos.remove(campos.size() - 1);
        String[] camposFinales = campos.toArray(new String[0]);
                
        String[] valores = data.recolectarDatos(nombreOriginal, camposFinales, request, response);

        Tabla tabla = new Tabla(nombreTabla, camposFinales, valores);
        DatosDAO dao = new DatosDAO();
        
        JOptionPane.showMessageDialog(null, Arrays.toString(camposFinales));

        if (dao.insertarDato(tabla.getNombreTabla(), tabla.getValoresTabla())) {
            JOptionPane.showMessageDialog(null, "Datos Insertados");
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