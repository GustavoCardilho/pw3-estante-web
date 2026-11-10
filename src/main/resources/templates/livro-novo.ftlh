<#include "cabecalho.ftlh">
<h1>Cadastrar livro</h1>
<#if erro??>
    <p>${erro}</p>
</#if>
<form method="post" action="/livros">
    <label>Título: <input type="text" name="titulo" value="${titulo!}"></label><br>
    <label>Autor: <input type="text" name="autor" value="${autor!}"></label><br>
    <label>Ano: <input type="text" name="ano" value="${(ano?c)!}"></label><br>
    <label>Preço: <input type="text" name="preco" value="${(preco?c)!}"></label><br>
    <button type="submit">Cadastrar</button>
</form>
