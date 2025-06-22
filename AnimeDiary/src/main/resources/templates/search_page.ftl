<#include "base.ftl"/>

<#macro title>
    Search
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_anime_catalog_page.css<#else>/css/light_theme/anime_catalog_page.css</#if>">
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_search_page.css<#else>/css/light_theme/search_page.css</#if>">
</#macro>

<#macro content>
    <div class="d-flex flex-column">
        <div class="d-flex justify-content-start ms-3 mb-4 search-container-custom">
            <form class="d-flex">
                <div class="d-inline-flex flex-column">
                    <input id="search_main_page" name="search_main_page" class="form-control me-2 input-custom search-input-custom" oninput="findAnimes()" type="search" placeholder="Поиск" aria-label="Поиск" >
                    <div id="found-list" class="d-inline-flex list-group position-absolute z-1 list-background-custom"></div> <!-- Результат поиска!! -->
                </div>
            </form>
            <button class="btn btn-secondary-custom mb-3 me-3" type="button" data-bs-toggle="modal" data-bs-target="#filter_modal">
                Фильтр
            </button>
        </div>
        <div id="anime_tyan_pic" class="d-flex justify-content-end">
            <div class="pic-search-container-custom">
                <img src="https://i.pinimg.com/originals/59/57/24/595724a185ee9672b8ad169ce1dc9f1a.png" class="pic-search-custom" alt="картинка">
            </div>
        </div>
    </div>
    <div id="filtered-animes"></div>
    <div class="modal fade" id="filter_modal" tabindex="-1" aria-labelledby="filter_modal_title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 id="filter_modal_title" class="modal-title exo-2-header fs-5">Фильтр</h1> <!-- Заголовок модального окна -->
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div id="modal_body" class="modal-body">
                    <#list genres as genre>
                        <div class="form-check">
                            <label for="genre${genre.getId()}">${genre.getName()}</label>
                            <input id="genre${genre.getId()}" class="form-check-input" type="checkbox" name="genres" value="${genre.getId()}">
                        </div>
                    </#list>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary-custom" data-bs-dismiss="modal">Закрыть</button>
                    <button type="button" class="btn btn-primary-custom" onclick=filterAnimes()>Применить фильтр</button>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/findAnimes.js"></script>
    <script type="application/javascript" src="/js/filterAnimes.js"></script>
</#macro>
<@page/>