<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Crie seu tópico</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Crie seu tópico</h1>
        <h4>Preencha as informações abaixo</h4>
        <h3 style="color:red;">${erro}</h3>
        <form action="inserirTopico">
            <h4>Título</h4></br>
            <textarea rows="1" cols="50" name="titulo"></textarea></br>
            <h4>Conteúdo</h4></br>
            <textarea rows="7" cols="75" name="conteudo"></textarea></br>
            <input type="submit" value="Criar tópico"/>
        </form>
    </body>
</html>
