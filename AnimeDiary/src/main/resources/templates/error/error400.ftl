<#include "error_base.ftl">

<#macro title>
    Ошибка 400
</#macro>

<#macro style>
    <link rel="stylesheet" href="/css/light_theme/error404.css">
</#macro>

<#macro content>
    <div class="d-inline-flex flex-column container-custom">
        <h1>Ошибка 400!</h1>
        <h4>Некорректный запрос.</h4>
        <a href="/main-page">Вернуться на главную страницу</a>
    </div>
</#macro>
<#macro scripts></#macro>
<@page/>
