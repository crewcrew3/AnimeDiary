<#include "base.ftl">

<#macro title>
    Studio Info
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_studio_info_page.css<#else>/css/light_theme/studio_info_page.css</#if>">
</#macro>

<#macro content>
    <h1 class="header-page-custom exo-2-header mb-5">${studio.getName()}</h1>
    <div class="d-flex">
        <div class="d-flex flex-column">
            <div class="card card-short-info-custom">
                <div class="card-body">
                    <h5 class="card-title exo-2-header mb-3">Информация</h5>

                    <h6 class="card-subtitle mb-2"><b>Год основания</b></h6>
                    <#if studio.getFoundationYear()??>
                        <p class="card-text">${studio.getFoundationYear()?c}</p>
                    <#else>
                        <p class="card-text">Неизвестно</p>
                    </#if>

                    <h6 class="card-subtitle mb-2"><b>Основатель</b></h6>
                    <#if studio.getFounderName()??>
                        <p class="card-text">${studio.getFounderName()}</p>
                    <#else>
                        <p class="card-text">Неизвестно</p>
                    </#if>

                    <h6 class="card-subtitle mb-2"><b>Количество работников</b></h6>
                    <#if studio.getNumberOfEmployees()??>
                        <p class="card-text">${studio.getNumberOfEmployees()}</p>
                    <#else>
                        <p class="card-text">Неизвестно</p>
                    </#if>

                    <h6 class="card-subtitle mb-2"><b>Официальный сайт</b></h6>
                    <#if studio.getOfficialSite()??>
                        <a href="${studio.getOfficialSite()}" class="card-text">
                            ${studio.getOfficialSite()}
                        </a>
                    <#else>
                        <p class="card-text">Ссылка недоступна</p>
                    </#if>
                </div>
            </div>
        </div>

        <div class="d-flex flex-column animes-list-custom">
            <#list studio.getAnimes() as anime>
                <div class="card card-custom mb-3">
                    <div class="row g-0">
                        <div class="col-3">
                            <div class="poster-container-custom">
                                <img src="/images/${anime.getPosterImgPath()}" class="img-fluid rounded-start image-poster-custom" alt="Постер">
                            </div>
                        </div>
                        <div class="col-9">
                            <div class="card-body">
                                <div class="d-flex justify-content-between">
                                    <h4 class="card-title exo-2-header">${anime.getName()}</h4>
                                    <span class="icon-rating-custom me-2">
                                        <i class="fa-solid fa-star"> ${anime.getRating()}</i>
                                    </span>
                                </div>
                                <p class="card-text">${anime.getSynopsis()}</p>
                                <a class="btn btn-primary-custom" href="/anime-info-page/${anime.getId()}">Подробнее</a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>

<@page/>