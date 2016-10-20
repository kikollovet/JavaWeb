<%-- 
    Document   : central
    Created on : 19/10/2016, 21:13:27
    Author     : Kiko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Caixa Eletrônico</title>
    </head>
    <body>
        <h1>Caixa Eletrônico</h1>
        <h4>Escolha a opção</h4>
        <form action="depositarController">
            <input type="submit" value="Depositar"/>
        </form>
        <form action="saldoController">
            <input type="submit" value="Ver saldo"/>
        </form>
        <form action="sacarController">
            <input type="submit" value="Sacar"/>
        </form>
    </body>
</html>
