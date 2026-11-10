<#include "cabecalho.ftlh">
<h1>Fale com a EstanteWeb</h1>
<#if erro??>
    <p>${erro}</p>
</#if>
<form method="post" action="/contato">
    <label>Nome: <input type="text" name="nome" value="${nome!}"></label><br>
    <label>Mensagem: <textarea name="mensagem"></textarea></label><br>
    <button type="submit">Enviar</button>
</form>
