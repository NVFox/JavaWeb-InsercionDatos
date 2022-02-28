<%-- 
    Document   : cuentas
    Created on : 21/02/2022, 12:31:04 a. m.
    Author     : usuario
--%>

<%@page import="modelo.Usuario"%>
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
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400&family=Roboto:wght@500&family=Ubuntu:wght@300&display=swap" rel="stylesheet">
        <link rel="shortcut icon" href="/Imagenes/favicon.ico" type="image/x-icon">
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();

            if (sesion.getAttribute("Usuario") != null) { %>
        <% Usuario usuario = (Usuario) sesion.getAttribute("Usuario"); %>
        <link rel="stylesheet" href="estilos.css"/>
        
        <header>
            <nav>
                <ul>
                    <li><h2>Aplicación de Banco</h2></li>
                    <li><a href="usuarios.jsp">Usuarios</a></li>
                    <li><a href="clientes.jsp">Clientes</a></li>
                    <li><a href="#">Cuentas</a></li>
                    <li><a href="lineas.jsp">Líneas</a></li>
                    <li><a href="creditos.jsp">Créditos</a></li>
                </ul>
            </nav>
            <a href="ServletLogoff" class="logout">Cerrar Sesión</a>
            <p><%= usuario.getNombre() %></p>
        </header>
        
        <form action="ServletDatos" method="post">
            <div class="form1">
                <legend>Formulario de Cuentas</legend>
            </div>
            <div class="form1">
                <input type="text" name="CodCuenta" placeholder="Código de Cuenta" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="TipoCuenta" placeholder="Tipo de Cuenta" class="campo" required>
            </div>
            <div class="form1">
                <input type="number" name="Saldo" placeholder="Saldo" class="campo" required>
            </div>
            <button type="submit" class="btn-main" name="btn-main-cuentas">
                Guardar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-update-cuentas">
                Actualizar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-delete-cuentas">
                Borrar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-cuentas";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"CodCuenta", "DocCli", "TipoCuenta", "Saldo"};

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
        <% } else { %>
        <link rel="stylesheet" href="./css/login.css"/>

        <section>
            <div class="contenedor">
                <form action="index.html">
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
