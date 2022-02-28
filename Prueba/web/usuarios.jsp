<%-- 
    Document   : index
    Created on : 18/02/2022, 1:27:11 p. m.
    Author     : SENA
--%>

<%@page import="modelo.Usuario"%>
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
                    <li><a href="#">Usuarios</a></li>
                    <li><a href="clientes.jsp">Clientes</a></li>
                    <li><a href="cuentas.jsp">Cuentas</a></li>
                    <li><a href="lineas.jsp">Líneas</a></li>
                    <li><a href="creditos.jsp">Créditos</a></li>
                </ul>
            </nav>
            <a href="ServletLogoff" class="logout">Cerrar Sesión</a>
            <p><%= usuario.getNombreReal() %></p>
        </header>
        
        <form action="ServletDatos" method="post">
            <div class="form1">
                <legend>Formulario de Usuarios</legend>
            </div>
            <div class="form1">
                <input type="text" name="DocCli" placeholder="Documento" class="campo" required>
            </div>
            <div class="form1">
                <input type="text" name="NomUsu" placeholder="Nombre de Usuario" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="Clave" placeholder="Clave" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="Rol" placeholder="Rol" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="Estado" placeholder="Estado" class="campo">
            </div>
            <div class="form1">
                <input type="text" name="Imagen" placeholder="Imagen" class="campo">
            </div>
            <button type="submit" class="btn-main" name="btn-main-usuarios">
                Guardar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-update-usuarios">
                Actualizar Registro
            </button>
            <button type="submit" class="btn-main" name="btn-delete-usuarios">
                Borrar Registro
            </button>
        </form>
        <%
            DatosDAO data = new DatosDAO();

            String nombreOriginal = "btn-main-usuarios";
            String nombreTabla = nombreOriginal.replace("btn-main-", "");
            String[] camposFinales = new String[]{"DocCli", "NomUsu", "Clave", "Rol", "Estado", "Imagen"};

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
                <% } %>
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
