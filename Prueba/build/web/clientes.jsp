<%-- 
    Document   : clientes
    Created on : 21/02/2022, 12:30:22 a. m.
    Author     : usuario
--%>

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
    </body>
</html>
