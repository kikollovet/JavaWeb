<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palavra Traduzida</title>
    </head>
    <body>
        <c:if test="${param.palavra != palavraTraduzida}">
            <h1>A palavra traduzida é <c:out value="${palavraTraduzida}"/></h1>
        </c:if>
        <c:if test="${param.palavra == palavraTraduzida}">
            <h1>Nosso banco de dados não tem tradução para a palavra <c:out value="${palavraTraduzida}"/></h1>
        </c:if>
    </body>
</html>
