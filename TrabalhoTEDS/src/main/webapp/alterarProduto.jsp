<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Produtos</title>
    </head>
    <body>
        <h1>Produtos Cadastrados</h1>

        <h2>Bebidas</h2>

        <c:forEach var="bebida" items="${bebidas}">
            <table>
                <td>
                    <form action="/TrabalhoTEDS/produto/bebida/alterar" method="POST">
                        Código: <input type="text" name="codigo_bebida" value="${bebida.codigo_bebida}" readonly="readonly">
                        Nome: <input type="text" name="nome_bebida" value="${bebida.nome}">
                        Descrição: <input type="text" name="descricao_bebida" value="${bebida.descricao}">
                        Preço: <input type="text" name="preco_bebida" value="${bebida.preco}">             
                        <input type="submit" value="Alterar" />
                    </form>      
                </td>        
                <td>
                    <form action="/TrabalhoTEDS/produto/bebida/remover" method="POST">
                        <input type="hidden" name="codigo_bebida" value="${bebida.codigo_bebida}">
                        <input type="submit" value="Remover" />
                    </form>
                </td>
            </table>
            <br>
        </c:forEach>


        <h2>Pratos</h2>

        <c:forEach var="prato" items="${pratos}">
            <table>
                <td>
                    <form action="/TrabalhoTEDS/produto/prato/alterar" method="POST">
                        Código: <input type="text" name="codigo_prato" value="${prato.codigo_prato}" readonly="readonly">
                        Nome: <input type="text" name="nome_prato" value="${prato.nome}"> 
                        Descrição: <input type="text" name="descricao_prato" value="${prato.descricao}">
                        Preço: <input type="text" name="preco_prato" value="${prato.preco}">
                        <input type="submit" value="Alterar" />
                    </form>
                </td>        
                <td>
                    <form action="/TrabalhoTEDS/produto/prato/remover" method="POST">
                        <input type="hidden" name="codigo_prato" value="${prato.codigo_prato}">
                        <input type="submit" value="Remover" />   
                    </form>
                </td>
            </table>
            <br>
        </c:forEach>

    </body>
</html>
