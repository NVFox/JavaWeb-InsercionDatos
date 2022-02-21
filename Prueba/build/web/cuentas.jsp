<%-- 
    Document   : cuentas
    Created on : 21/02/2022, 12:31:04 a. m.
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
        </form>
    </body>
</html>
