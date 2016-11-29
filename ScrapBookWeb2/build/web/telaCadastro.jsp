
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro no ScrapBookWeb</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Bem vindo a nossa tela de Cadastro</h1>
        <h4>Preencha o formulário</h4>
        <h3 style="color:red;">${erro}</h3>
        <form method="POST" action="efetuarCadastro">
            Login: <input type="text" name="login" /></br>
            Email: <input type="text" name="email" /></br>
            Nome: <input type="text" name="nome" /></br>
            Senha: <input type="password" name="senha" /></br>
            <input type="submit" value="Efetuar cadastro"/>
        </form>
    </body>
</html>
