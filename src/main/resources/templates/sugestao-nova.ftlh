<#include "cabecalho.ftlh">
<h1>Sugerir um livro</h1>
<#if erro??>
    <p>${erro}</p>
</#if>
<form method="post" action="/sugestoes">
    <label>Seu nome: <input type="text" name="nomeCliente" value="${nomeCliente!}"></label><br>
    <label>Livro sugerido: <input type="text" name="livroSugerido" value="${livroSugerido!}"></label><br>
    <button type="submit">Enviar sugestão</button>
</form>
