<%-- 
    Document   : index
    Created on : 18/02/2022, 1:27:11 p. m.
    Author     : SENA
--%>

<%@page import="java.util.Arrays"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="controlador.DataMethods"%>
<%@page import="modelo.Tabla"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.DatosDAO"%>
<%@page import="controlador.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
        <link rel="stylesheet" href="estilos.css"/>
    </head>
    <body>
        <form action="ServletDatos" method="post">
            <div class="form1">
                <legend>Formulario de Usuarios</legend>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="NomUsu" placeholder="Nombre de Usuario" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="Clave" placeholder="Clave" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="Rol" placeholder="Rol" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="Estado" placeholder="Estado" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="Imagen" placeholder="Imagen" class="campo" required>
            </div>
            <button type="submit" class="btn-main" name="btn-main-usuarios">
                Guardar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-usuarios";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"DocCli", "NomUsu", "Clave", "Rol", "Estado", "Imagen"};

            Tabla tabla = data.consultarDatos(nombreTabla, camposFinales);
            ArrayList<String[]> valores = tabla.getValoresTotales();
        %>
        <table>
            <thead>
                <tr>
                    <% for (int i = 0; i < camposFinales.length; i++) {%>
                        <th><%= camposFinales[i]%></th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i < valores.size(); i++) { %>
                <tr>
                    <td><%= Arrays.toString(valores.get(i)) %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
