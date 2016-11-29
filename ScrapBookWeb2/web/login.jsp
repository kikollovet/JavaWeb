

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ScrapBookWeb</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Bem vindo ao ScrapBookWeb</h1>
        <h4>Digite suas informações para efetuar o login</h4>
        <h3 style="color:red;">${erro}</h3>
        <form method="POST" action="login">
            Login: <input type="text" name="login" />
            Senha: <input type="password" name="senha" />
            <input type="submit" value="Fazer login" />
        </form>
        <p><a href="telaCadastro">Clique aqui</a> para efetuar cadastro</p> 
    </body>
</html>
