<%-- 
    Document   : clientes
    Created on : 21/02/2022, 12:30:22 a. m.
    Author     : usuario
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Tabla"%>
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
                <legend>Formulario de Clientes</legend>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="NomCli" placeholder="Nombre" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="ApeCli" placeholder="Apellido" class="campo" required>
            </div>
            <div class="form1">
                <input type="email" name="CorreoCli" placeholder="Correo" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="CelularCli" placeholder="Celular" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="SexoCli" placeholder="Sexo" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="FechaNacCli" placeholder="Fecha de Nacimiento" class="campo" required>
            </div>
            <button type="submit" class="btn-main" name="btn-main-clientes">
                Guardar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-clientes";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"DocCli", "NomCli", "ApeCli", "CorreoCli", "CelularCli", "SexoCli", "FechaNacCli"};

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
                    <% String[] valoresIndividuales = valores.get(i); %>
                    <% for (int x = 0; x < valoresIndividuales.length; x++) { %>
                    <td><%= valoresIndividuales[x] %></td>
                    <% } %>
                </tr>
                <% } %>
            </tbody>
        </table>
    </body>
</html>
