<%-- 
    Document   : creditos
    Created on : 21/02/2022, 12:30:51 a. m.
    Author     : usuario
--%>

<%@page import="modelo.DatosDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Tabla"%>
<%@page import="controlador.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
        <link rel="stylesheet" href="./css/estilos.css"/>
    </head>
    <body>
        <form action="ServletDatos" method="post">
            <div class="form1">
                <legend>Formulario de Créditos</legend>
            </div>
            <div class="form1">
                <input type="text" name="CodigoCredito" placeholder="Código de Crédito" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="CodLinea" placeholder="Codigo de Línea" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="MontoPrestado" placeholder="Monto Prestado" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="FechaAproba" placeholder="Fecha de Aprobación" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="Plazo" placeholder="Plazo" class="campo" required>
            </div>
            <button type="submit" class="btn-main" name="btn-main-creditos">
                Guardar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-creditos";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"CodigoCredito", "DocCli", "CodLinea", "MontoPrestado", "FechaAproba", "Plazo"};

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
