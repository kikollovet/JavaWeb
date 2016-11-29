

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Tópico</title>
    </head>
    <body>
        <h1>Título do Tópico: <c:out value="${topico.titulo}"/></h1>
        <h3>Autor: <c:out value="${autorTopico}"/></h3>
        <h4>Conteúdo do tópico</h4>
        <p><c:out value="${topico.conteudo}"/></p>
        <hr>
        <h4 style="color:red;">${erro}</h4>
        <h3>Comentários</h3>
        <c:forEach var="dados" items="${mapa}">
            <br>
            <p style="color:blue;"><c:out value="${dados.value}"/></p>
            <p><c:out value="${dados.key.comentario}"/></p>
            <br>
        </c:forEach>
        <hr>
        <h4>Faça um comentario</h4>
        <form action="fazerComentario">
            <textarea rows="7" cols="75" name="comentario"></textarea></br>
            <input type="hidden" name="id" value="${topico.id_topico}"/>
            <input type="submit" value="Enviar Comentario"/>
        </form>
            <hr>
            <p><a href="telaTopicos">Clique aqui</a> para voltar para a tela de tópicos</p>
    </body>
</html>
