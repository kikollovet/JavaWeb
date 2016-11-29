

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>Ranking ScrapBookWeb</title>
    </head>
    <body>
        <h1>Ranking dos usuários</h1>
        <hr>
        
            <table>
                <tr>
                    <th>Colocação</th>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Pontos</th>
                </tr>
                <c:forEach var="dados" items="${listaRanking}" varStatus="posicao">
                    <tr>
                        <td><c:out value="${posicao.index + 1}"/></td>
                        <td><c:out value="${dados.nome}"/></td>
                        <td><c:out value="${dados.login}"/></td>
                        <td><c:out value="${dados.pontos}"/></td>
                    </tr>
                </c:forEach>
            </table>
        <p><a href="telaTopicos">Clique aqui</a> para voltar a tela de exibição dos tópicos!</p>
    </body>
</html>
