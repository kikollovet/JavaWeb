<%-- 
    Document   : depositar
    Created on : 19/10/2016, 21:03:52
    Author     : Kiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de depósito</title>
    </head>
    <body>
        <h1>Insira o valor do depósito</h1>
        <form action="efetuarDepositoController">
            <input type="text" name="valor"/>
            <input type="submit" value="Depositar"/>
        </form>
    </body>
</html>
