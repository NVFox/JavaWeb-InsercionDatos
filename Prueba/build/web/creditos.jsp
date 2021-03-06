<%-- 
    Document   : creditos
    Created on : 21/02/2022, 12:30:51 a. m.
    Author     : usuario
--%>

<%@page import="modelo.Usuario"%>
<%@page import="modelo.DatosDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Tabla"%>
<%@page import="controlador.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario</title>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400&family=Roboto:wght@500&family=Ubuntu:wght@300&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="/Imagenes/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();

            if (sesion.getAttribute("Usuario") != null) { %>
        <% Usuario usuario = (Usuario) sesion.getAttribute("Usuario"); %>
        <link rel="stylesheet" href="css/estilos.css"/>
        
        <header>
            <nav>
                <ul>
                    <li><h2>Aplicación de Banco</h2></li>
                    <li><a href="usuarios.jsp">Usuarios</a></li>
                    <li><a href="clientes.jsp">Clientes</a></li>
                    <li><a href="cuentas.jsp">Cuentas</a></li>
                    <li><a href="lineas.jsp">Líneas</a></li>
                    <li><a href="#">Créditos</a></li>
                </ul>
            </nav>
            <a href="ServletLogoff" class="logout">Cerrar Sesión</a>
            <p><%= usuario.getNombreReal()%></p>
        </header>

        <form action="ServletDatos" method="post">
            <div class="form1">
                <legend>Formulario de Créditos</legend>
            </div>
            <div class="form1">
                <input type="text" name="CodigoCredito" placeholder="Código de Crédito" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="CodLinea" placeholder="Codigo de Línea" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="MontoPrestado" placeholder="Monto Prestado" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="FechaAproba" placeholder="Fecha de Aprobación" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="Plazo" placeholder="Plazo" class="campo">
            </div>
            <button type="submit" class="btn-main" name="btn-main-creditos">
                Guardar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-update-creditos">
                Actualizar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-delete-creditos">
                Borrar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-creditos";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"CodigoCredito", "DocCli", "CodLinea", "MontoPrestado", "FechaAproba", "Plazo"};

            Tabla tabla = data.consultarDatos(nombreTabla, camposFinales, usuario);
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
                    <% for (int x = 0; x < valoresIndividuales.length; x++) {%>
                    <td><%= valoresIndividuales[x]%></td>
                    <% } %>
                </tr>
                <% }%>
            </tbody>
        </table>
        <% } else { %>
        <link rel="stylesheet" href="./css/login.css"/>

        <section>
            <div class="contenedor">
                <form action="http://localhost:8080/Prueba">
                    <div class="log">
                        <h2>Debe iniciar sesión para continuar</h2>
                    </div>
                    <input type="submit" name="btn-login" class="btn-login" value="Iniciar Sesión">
                </form>
            </div>
        </section>
        <% }%>
    </body>
</html>
