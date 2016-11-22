<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
    </head>
    <body>
        <c:forEach var="pedido" items="${pedidos}" >          
            <p>Codigo: ${pedido.codigo}</p>
            <p>Data: ${pedido.dataCompra} </p>
            <p>Valor: ${pedido.valor} </p>
            <c:forEach var="item" items="${pedido.itens}" >
                <p>Quantidade: ${item.quantidade}</p>
                <p>Produto: ${item.produto.nome} </p>
                <p>Preco Produto: ${item.produto.preco} </p>
                <p>Preco Item: ${item.quantidade * item.produto.preco}</p>
            </c:forEach>
        </c:forEach>
        <form action="/TrabalhoTEDS/index.html">
            <input type="submit" value="Voltar" />
        </form>
    </body>
</html>
