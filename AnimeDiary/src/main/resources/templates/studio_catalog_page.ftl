<#include "base.ftl"/>

<#macro title>
    Studios Catalog
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_studio_catalog_page.css<#else>/css/light_theme/studio_catalog_page.css</#if>">
</#macro>

<#macro content>
    <h1 class="header-page-custom exo-2-header">Каталог студий</h1>
    <div class="d-flex flex-column">
        <div class="card background-card-custom">
            <div class="card-body">
                <#list studios as studio>
                    <div class="mb-5">
                        <h5 class="studio-title-custom mb-3"><b>${studio.getName()}</b></h5>
                        <h6 class="card-subtitle studio-subtitle-custom mb-2"><b>Год основания</b></h6>
                        <#if studio.getFoundationYear()??>
                            <p class="card-text">${studio.getFoundationYear()?c}</p>
                        <#else>
                            <p class="card-text">Неизвестно</p>
                        </#if>

                        <h6 class="card-subtitle studio-subtitle-custom mb-2"><b>Основатель</b></h6>
                        <#if studio.getFounderName()??>
                            <p class="card-text">${studio.getFounderName()}</p>
                        <#else>
                            <p class="card-text">Неизвестно</p>
                        </#if>

                        <h6 class="card-subtitle studio-subtitle-custom mb-2"><b>Количество работников</b></h6>
                        <#if studio.getNumberOfEmployees()??>
                            <p class="card-text">${studio.getNumberOfEmployees()}</p>
                        <#else>
                            <p class="card-text">Неизвестно</p>
                        </#if>

                        <h6 class="card-subtitle studio-subtitle-custom mb-2"><b>Официальный сайт</b></h6>
                        <#if studio.getOfficialSite()??>
                            <a href="${studio.getOfficialSite()}" class="card-text">
                                ${studio.getOfficialSite()}
                            </a>
                        <#else>
                            <p class="card-text">Ссылка недоступна</p>
                        </#if>
                        <div class="d-flex justify-content-end">
                            <a class="btn btn-primary-custom" href="/studio-info-page/${studio.getId()}">Посмотреть работы</a>
                        </div>
                        <hr>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>
<@page/>