<%-- 
    Document   : sacar
    Created on : 19/10/2016, 21:40:56
    Author     : Kiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Saque</title>
    </head>
    <body>
        <h4>Digite o valor para saque e aperte o botão</h4>
        <form action="efetuarSaqueController">
            <input type="text" name="valor"/>
            <input type="submit" value="Efetuar saque"/>
        </form>
    </body>
</html>
