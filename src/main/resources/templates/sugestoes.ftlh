<#include "cabecalho.ftlh">
<h1>Sugestões de leitura</h1>
<#if enviada?? && enviada>
    <p>Sugestão enviada com sucesso!</p>
</#if>
<#if sugestoes?size == 0>
    <p>Nenhuma sugestão recebida ainda.</p>
<#else>
    <ul>
    <#list sugestoes as sugestao>
        <li>${sugestao.nomeCliente} sugeriu: ${sugestao.livroSugerido}</li>
    </#list>
    </ul>
</#if>
