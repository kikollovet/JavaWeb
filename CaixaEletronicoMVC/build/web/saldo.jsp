<%-- 
    Document   : saldo
    Created on : 19/10/2016, 21:27:51
    Author     : Kiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saldo</title>
    </head>
    <body>
        <h4>Seu saldo é R$ ${saldo},00</h4>
        <form action="voltarCentralController">
            <input type="submit" value="Retornar a tela principal"/>
        </form>
    </body>
</html>
