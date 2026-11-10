<#include "cabecalho.ftlh">
<h1>${livro.titulo}</h1>
<p>Autor: ${livro.autor}</p>
<p>Ano: ${livro.ano?c}</p>
<p>Preço: R$ ${livro.preco?string["0.00"]}</p>

<#if mesmoAutor?? && mesmoAutor?size gt 0>
    <h2>Outros títulos de ${livro.autor}</h2>
    <ul>
    <#list mesmoAutor as outro>
        <li><a href="/livros/${outro.id?c}">${outro.titulo}</a></li>
    </#list>
    </ul>
</#if>

<p><a href="/livros/html">Voltar para o acervo</a></p>
