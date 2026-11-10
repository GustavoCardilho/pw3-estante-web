<#include "cabecalho.ftlh">
<h1>Acervo EstanteWeb</h1>
<p>
    Ordenar por:
    <a href="/livros/html?ordem=titulo">título</a> |
    <a href="/livros/html?ordem=autor">autor</a> |
    <a href="/livros/html?ordem=ano">ano</a> |
    <a href="/livros/html?ordem=preco">preço</a> |
    <a href="/livros/html">padrão</a>
</p>
<#if livros?size == 0>
    <p>Nenhum livro no acervo no momento.</p>
<#else>
    <ul>
    <#list livros as livro>
        <li><a href="/livros/${livro.id?c}">${livro.titulo}</a>, de ${livro.autor} (R$ ${livro.preco?c})</li>
    </#list>
    </ul>
</#if>
