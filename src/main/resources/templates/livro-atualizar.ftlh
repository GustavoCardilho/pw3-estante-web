<#include "cabecalho.ftlh">
<h1>Atualizar livro</h1>
<#if erro??>
    <p>${erro}</p>
</#if>
<form method="post" action="/livros/atualizar/${id?c}">
    <input type="hidden" name="_method" value="put">
    <label>Título: <input type="text" name="titulo" value="${titulo!}"></label><br>
    <label>Autor: <input type="text" name="autor" value="${autor!}"></label><br>
    <label>Ano: <input type="text" name="ano" value="${(ano?c)!}"></label><br>
    <label>Preço: <input type="text" name="preco" value="${(preco?c)!}"></label><br>
    <button type="submit">Atualizar</button>
</form>

<form method="post" action="/livros/deletar/${id?c}" onsubmit="return confirm('Excluir este livro?');">
    <input type="hidden" name="_method" value="delete">
    <button type="submit">Excluir</button>
</form>

<p><a href="/livros/html">Voltar para o acervo</a></p>
