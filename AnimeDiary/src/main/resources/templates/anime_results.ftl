<#include "base.ftl"/>

<#macro title>
    Find Anime By Picture
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/!!<#else>/css/light_theme/!!</#if>">
</#macro>

<#macro content>
    <div class="d-flex flex-column" style="margin-top: 100px">
        <h1>Результаты поиска</h1>

        <a href="/anime-search-by-pic">Новый поиск</a>

        <#if nativeTitle??>
            <h3>Найденное аниме:</h3>
            <p>Оригинальное название: <span>${nativeTitle}</span></p>
            <p>Ромадзи: <span>${romajiTitle}</span></p>
            <p>Английское название:
                <span>${englishTitle!''}</span>
                <#if !englishTitle??>Нет английского названия</#if>
            </p>
            <p>Совпадение: <span>${similarity?string["0.####"]}</span></p>

            <h4>Исходное изображение:</h4>
            <img src="${imageUrl}" style="width: 500px; height: 270px;" alt="Загруженное изображение">
        <#else>
            <p>Аниме по этому изображению не найдено.</p>
        </#if>
    </div>
</#macro>

<#macro scripts></#macro>

<@page/>