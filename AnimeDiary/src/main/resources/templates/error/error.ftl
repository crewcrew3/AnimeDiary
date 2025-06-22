<#include "error_base.ftl">

<#macro title>
    Ошибка
</#macro>

<#macro style>
    <link rel="stylesheet" href="/css/light_theme/error404.css">
</#macro>

<#macro content>
    <div class="d-inline-flex flex-column container-custom">
        <h1>Ошибка!</h1>
        <h4>Упс! Что-то пошло не так...</h4>
        <a href="/main-page">Вернуться на главную страницу</a>
    </div>
</#macro>
<#macro scripts></#macro>
<@page/>
