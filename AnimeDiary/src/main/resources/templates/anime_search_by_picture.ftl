<#include "base.ftl"/>

<#macro title>
    Find Anime By Picture
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/!!<#else>/css/light_theme/!!</#if>">
</#macro>

<#macro content>
    <div class="d-flex" style="margin-top: 100px">
        <form method="POST" action="/anime-search-by-pic">
            <@csrf/>
            <input class="form-control input-custom" type="text" name="imageUrl"
                   placeholder="Введите URL изображения"
                   size="50" required>
            <button class="btn btn-primary-custom mt-2" type="submit">Найти</button>
        </form>
    </div>
</#macro>

<#macro scripts></#macro>
<@page/>