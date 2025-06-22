<#include "base.ftl">

<#macro title>
    Anime Info
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_anime_info_page.css<#else>/css/light_theme/anime_info_page.css</#if>"
</#macro>

<#macro content>
    <div class="d-flex justify-content-center position-fixed z-1 notification-container-custom">
        <div id="notification"></div> <!-- Уведомление после добавления в список -->
    </div>
    <div class="d-flex">
        <div class="d-inline-flex flex-column short-info-custom">
            <div class="card card-poster-custom mb-2">
                <img src="/images/${anime.getPosterImgPath()}" class="card-img" alt="Постер">
                <div class="card-img-overlay"></div>
            </div>
            <#if anime.getLinkToWatch()??>
                <a class="btn btn-primary-custom mb-4" href="${anime.getLinkToWatch()}">Смотреть онлайн</a>
            <#else>
                <button class="btn btn-inactive-custom mb-4">Ссылка недоступна</button>
            </#if>

            <p class="exo-2-header">На каком вы этапе просмотра?</p>
            <div class="dropdown mb-4">
                <#if is_authorized == true>
                    <button id="list" class="btn btn-third-custom dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Добавить в список
                    </button>
                <#else>
                    <button id="list" class="btn btn-third-custom disabled" type="button" aria-expanded="false">
                        Для добавления в список авторизируйтесь
                    </button>
                </#if>
                <ul class="dropdown-menu">
                    <li class="dropdown-item dropdown-item-list" onclick="addToList('В планах', ${anime.getId()})">В планах</li>
                    <li class="dropdown-item dropdown-item-list" onclick="addToList('Смотрю', ${anime.getId()})">Смотрю</li>
                    <li class="dropdown-item dropdown-item-list" onclick="addToList('Просмотрено', ${anime.getId()})">Просмотрено</li>
                    <li class="dropdown-item dropdown-item-list" onclick="addToList('Брошено', ${anime.getId()})">Брошено</li>
                </ul>
            </div>

            <p class="exo-2-header">Выразите свое особое отношение</p>
            <div class="dropdown mb-4">
                <#if is_authorized == true>
                    <button id="special_list" class="btn btn-third-custom dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Добавить в список
                    </button>
                <#else>
                    <button id="special_list" class="btn btn-third-custom disabled" type="button" aria-expanded="false">
                        Для добавления в список авторизируйтесь
                    </button>
                </#if>
                <ul class="dropdown-menu">
                    <li class="dropdown-item dropdown-item-special-list" onclick="addToSpecialList('Любимое', ${anime.getId()})">Любимое</li>
                    <li class="dropdown-item dropdown-item-special-list" onclick="addToSpecialList('Плохое', ${anime.getId()})">Плохое</li>
                </ul>
            </div>

            <div class="card card-short-info-custom">
                <div class="card-body">
                    <h5 class="card-title exo-2-header mb-3">Информация</h5>

                    <h6 class="card-subtitle mb-2"><b>Тип</b></h6>
                    <p class="card-text">${anime.getType().getName()}</p>

                    <h6 class="card-subtitle mb-2"><b>Выпуск</b></h6>
                    <#if anime.getYear()??>
                        <p class="card-text">${anime.getYear()?c}</p>
                    <#else>
                        <p class="card-text">Неизвестно</p>
                    </#if>

                    <h6 class="card-subtitle mb-2"><b>Статус</b></h6>
                    <p class="card-text">${anime.getStatus().getName()}</p>

                    <h6 class="card-subtitle mb-2"><b>Количество эпизодов</b></h6>
                    <p class="card-text">${anime.getNumberOfEpisodes()}</p>

                    <h6 class="card-subtitle mb-2"><b>Студии</b></h6>
                    <#list anime.getStudios() as studio>
                        <a class="me-3" href="/studio-info-page/${studio.getId()}" class="card-text">
                            ${studio.getName()}
                        </a>
                    </#list>
                </div>
            </div>
        </div>

        <div class="d-flex flex-column long-info-custom">
            <div class="d-flex justify-content-between">
                <h1 class="anime-title-custom exo-2-header mb-5">${anime.getName()}</h1>
                    <span id="ic_rating" class="icon-rating-custom me-2">
                        <i class="fa-solid fa-star">${anime.getRating()}</i>
                    </span>
            </div>
            <div class="card card-long-info-custom">
                <div class="card-body">
                    <p class="mb-4">${anime.getSynopsis()}</p>
                    <h6 class="card-subtitle mb-2"><b>Жанры</b></h6>
                    <div class="d-flex">
                        <#list anime.getGenres() as genre>
                            <p class="card-text me-3">${genre.getName()}</p>
                        </#list>
                    </div>
                    <div class="d-flex justify-content-end">
                        <#if is_in_user_list??>
                            <#if is_in_user_list == false>
                                <div id="review_btn">
                                    <a class="btn btn-primary-custom me-3 disabled" >Прежде чем написать рецензию, добавьте аниме в какой-нибудь список</a>
                                </div>
                            <#else>
                                <#if user_review??>
                                    <a id="btn_edit_review" class="btn btn-primary-custom me-3" href="/anime-info-page/${anime.getId()}/review-page">Редактировать рецензию</a>
                                <#else>
                                    <a class="btn btn-primary-custom me-3" href="/anime-info-page/${anime.getId()}/review-page">Написать рецензию</a>
                                </#if>
                            </#if>
                            <a class="btn btn-secondary-custom" href="/anime-info-page/${anime.getId()}/forum-page">Перейти в обсуждение</a>
                        <#else>
                            <button class="btn btn-primary-custom disabled" >Чтобы написать рецензию и зайти на форум, авторизируйтесь</button>
                        </#if>
                    </div>
                </div>
            </div>

            <#if user_review??>
                <h4 id="my_review" class="anime-title-custom exo-2-header mt-5">Моя рецензия</h4>
                <div class="card user-review-custom mt-3">
                    <div class="card-body">
                        <p class="mb-2"><b>Рисовка:</b> ${user_review.getRateDrawing()}</p>
                        <p class="mb-2"><b>Сюжет:</b> ${user_review.getRatePlot()}</p>
                        <p class="mb-2"><b>Персонажи:</b> ${user_review.getRateCharacters()}</p>
                        <p class="mb-2"><b>Опенинг:</b> ${user_review.getRateOpening()}</p>
                        <p class="mb-2">${user_review.getReviewText()}</p>
                        <div class="d-flex justify-content-end">
                            <i>${user_review.getReviewDate()?datetime}</i>
                        </div>
                        <div class="d-flex justify-content-end">
                            <span class="icon-delete-custom me-2 mt-2">
                                <i class="fa fa-trash" onclick="deleteReview(${user_review.getId()}, ${anime.getId()}, this)"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </#if>

            <h4 class="anime-title-custom exo-2-header mt-5">Рецензии</h4>
            <#if anime_reviews?size == 0>
                <h5 class="message-empty-list-custom">Рецензий еще нет. Будьте первым! </h5>
            </#if>
            <#list anime_reviews as review>
                <div class="card card-long-info-custom mt-3">
                    <div class="card-body">
                        <p class="review-title-custom mb-2">Пользователь <b>${review.getUser().getNickname()}</b> поделился своим мнением</p>
                        <p class="mb-2"><b>Рисовка:</b> ${review.getRateDrawing()}</p>
                        <p class="mb-2"><b>Сюжет:</b> ${review.getRatePlot()}</p>
                        <p class="mb-2"><b>Персонажи:</b> ${review.getRateCharacters()}</p>
                        <p class="mb-2"><b>Опенинг:</b> ${review.getRateOpening()}</p>
                        <p class="mb-2">${review.getReviewText()}</p>
                        <div class="d-flex justify-content-end">
                            <i>${review.getReviewDate()?datetime}</i>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/showNotification.js"></script>
    <script type="application/javascript" src="/js/addToList.js"></script>
    <script type="application/javascript" src="/js/addToSpecialList.js"></script>
    <script type="application/javascript" src="/js/deleteReview.js"></script>
</#macro>

<@page/>



