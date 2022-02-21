<%-- 
    Document   : creditos
    Created on : 21/02/2022, 12:30:51 a. m.
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
    </body>
</html>
