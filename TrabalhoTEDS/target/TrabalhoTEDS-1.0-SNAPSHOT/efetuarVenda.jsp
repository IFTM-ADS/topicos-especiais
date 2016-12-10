<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compra</title>
    </head>
    <body>  
        
        <h2>Bebidas</h2>
        <form action="/TrabalhoTEDS/carrinho/bebida/adicionar" method="POST">
            Quantidade: <input type="text" name="quantidade" value="" id="quantidadebebida"/>
            <select name="codigo" id="bebidaid">  
                <c:forEach var="bebida" items="${bebidas}">
                    <option value="${bebida.codigo_bebida}">${bebida.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Adicionar no Carrinho" id="adicionarbebida"/>
        </form>
        
        <h2>Pratos</h2>
        <form action="/TrabalhoTEDS/carrinho/prato/adicionar" method="POST">
            Quantidade: <input type="text" name="quantidade" value="" id="quantidadeid"/>
            <select name="codigo" id="produto">  
                <c:forEach var="prato" items="${pratos}">
                    <option value="${prato.codigo_prato}">${prato.nome}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Adicionar no Carrinho" id="adicionar"/>
        </form>
        
        <br><h3>Itens do Carrinho:</h3>
        <c:forEach var="item" items="${carrinho.itens}">
            ${item.quantidade} ${item.produto.nome}<br>
        </c:forEach>
                      
        <form action="/TrabalhoTEDS/vendas/finalizar">
            <input type="submit" value="Finalizar Pedido" id="finalizar"/>
        </form>
</html>
