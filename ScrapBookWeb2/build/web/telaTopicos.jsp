
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Topicos</title>
    </head>
    <body>
        <h1>Lista dos tópicos</h1>
        <hr>
        <table border="1px">
            <tr>
                <th>Nome do usuario</th>
                <th>Título do tópico</th>
                <th>Visualizar tópico</th>
            </tr>
            <c:forEach var="dado" items="${mapa}">
                <tr>
                    <td><c:out value="${dado.value}"/></td>
                    <td><c:out value="${dado.key.titulo}"/></td>
                    <td><a href="exibeTopico?id=<c:out value="${dado.key.id_topico}"/>">Visualizar tópico</a></td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="telaRanking">Clique aqui</a> para ver o Ranking!</p>
        <p><a href="telaInsereTopico">Clique aqui</a> para criar um novo tópico</p>
    </body>
</html>
