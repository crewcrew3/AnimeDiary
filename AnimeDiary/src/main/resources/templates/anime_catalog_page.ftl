<#include "base.ftl"/>

<#macro title>
    Anime Catalog
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_anime_catalog_page.css<#else>/css/light_theme/anime_catalog_page.css</#if>">
</#macro>

<#macro content>
    <div class="d-flex">
        <h1 class="header-page-custom exo-2-header me-4">Каталог аниме</h1>
        <#if is_admin>
            <a class="btn btn-primary-custom add-anime-btn-custom" href="/create-anime-page" role="button">Добавить аниме</a>
        </#if>
    </div>
    <div class="card background-card-custom">
        <div class="card-body">
            <div class="row row-cols-5 g-4"> <!-- По 5 столбцов в каждой строке -->
                <#list animes as anime>
                    <div class="col">
                        <div class="card card-custom">
                            <div class="poster-container-custom">
                                <img src="/images/${anime.getPosterImgPath()}" class="card-img image-poster-custom" alt="Постер">
                            </div>
                            <div class="overlay"></div>
                            <div class="card-img-overlay d-flex flex-column justify-content-end">
                                <div class="container text-center">
                                    <h5 class="card-title anime-name-text-custom exo-2-header">${anime.getName()}</h5>
                                    <a class="btn btn-learn-more" href="/anime-info-page/${anime.getId()}">Подробнее</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>
<@page/>