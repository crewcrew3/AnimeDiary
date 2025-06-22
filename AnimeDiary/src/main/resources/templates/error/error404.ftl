<#include "error_base.ftl">

<#macro title>
    Ошибка 404
</#macro>

<#macro style>
    <link rel="stylesheet" href="/css/light_theme/error404.css">
</#macro>

<#macro content>
    <div class="d-inline-flex flex-column container-custom">
        <h1>Ошибка 404!</h1>
        <h4>Страница не найдена.</h4>
        <a href="/main-page">Вернуться на главную страницу</a>
    </div>
</#macro>
<#macro scripts></#macro>
<@page/>
