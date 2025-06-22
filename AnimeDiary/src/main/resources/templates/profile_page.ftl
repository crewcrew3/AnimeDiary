<#include "base.ftl">

<#macro title>
    Profile
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_profile_page.css<#else>/css/light_theme/profile_page.css</#if>">
</#macro>

<#macro content>
    <h1 class="header-page-custom exo-2-header">Профиль</h1>
    <div class="d-flex">
        <div class="d-flex flex-column">
            <div class="card card-profile-custom mt-4">
                <div class="card-body">
                    <div class="d-inline-flex flex-column">
                        <div class="img-container-custom mb-2">
                            <img src="/images/${user.getPhotoURL()}" class="image-avatar-custom" alt="Картинка">
                        </div>
                        <a class="btn btn-primary-custom btn-custom position-absolute z-1" title="Редактировать профиль" href="/profile-page/edit">
                            <span class="icon-send-custom">
                                <i class="fa-solid fa-pen-to-square"></i>
                            </span>
                        </a>
                    </div>

                    <h5 class="exo-2-header mb-2">Никнэйм</h5>
                    <p class="mb-4">${user.getNickname()}</p>

                    <h5 class="exo-2-header mb-2">Почта</h5>
                    <p class="mb-4">${user.getEmail()}</p>

                    <h5 class="exo-2-header mb-2">Возраст</h5>
                    <#if user.getAge()??>
                        <p class="mb-4">${user.getAge()}</p>
                    <#else>
                        <p class="mb-4">Не указано</p>
                    </#if>

                    <h5 class="exo-2-header mb-2">Роль</h5>
                    <p class="mb-4">${role}</p>
                </div>
            </div>
        </div>
        <div class="d-flex flex-column animes-custom">
            <div class="card background-card-custom mt-4">
                <div class="card-body">
                    <div class="list-group list-group-custom mb-4">
                        <a id="all" onclick="switchList('Все')" class="list-group-item-custom list-group-item list-group-item-action">Все</a>
                        <a id="plans" onclick="switchList('В планах')" class="list-group-item-custom list-group-item list-group-item-action">В планах</a>
                        <a id="watching" onclick="switchList('Смотрю')" class="list-group-item-custom list-group-item list-group-item-action">Смотрю</a>
                        <a id="watched" onclick="switchList('Просмотрено')" class="list-group-item-custom list-group-item list-group-item-action">Просмотрено</a>
                        <a id="dropped" onclick="switchList('Брошено')" class="list-group-item-custom list-group-item list-group-item-action">Брошено</a>
                        <a id="like" onclick="switchList('Любимое')" class="list-group-item-custom list-group-item list-group-item-action">Любимое</a>
                        <a id="dislike" onclick="switchList('Плохое')" class="list-group-item-custom list-group-item list-group-item-action">Плохое</a>
                    </div>
                    <hr>

                    <div id="list_of_animes"> <!-- будем менять этот блок в зависимости от выбранной кнопки -->
                        <#list animes as anime>
                            <div class="card card-anime-review-custom mb-3">
                                <div class="row g-0">
                                    <div class="col-3">
                                        <img src="/images/${anime.getPosterImgPath()}" class="img-fluid rounded image-poster-custom mb-4" alt="Постер">
                                    </div>
                                    <div class="col-9">
                                        <div class="card-body">
                                            <h5 class="card-title exo-2-header mb-3">${anime.getName()}</h5>
                                            ${anime.getSynopsis()}
                                            <div class="d-flex justify-content-end">
                                                <!-- Кнопка-триггер модального окна -->
                                                <button onclick="openReview(${anime.getId()})" class="btn btn-secondary-custom mb-3 mt-3 me-3" type="button" data-bs-toggle="modal" data-bs-target="#review_modal">
                                                    Посмотреть рецензию
                                                </button>
                                                <a class="btn btn-primary-custom mb-3 mt-3" href="/anime-info-page/${anime.getId()}">Подробнее</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                        </#list>
                    </div>

                    <!-- Модальное окно -->
                    <div class="modal fade" id="review_modal" tabindex="-1" aria-labelledby="review_modal_title" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 id="review_modal_title" class="modal-title exo-2-header fs-5"></h1> <!-- Заголовок модального окна -->
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                                </div>
                                <div id="modal_body" class="modal-body"></div> <!-- Рецензия -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary-custom" data-bs-dismiss="modal">Закрыть</button>
                                    <div id="review_button"></div> <!-- Для кнопки для рецензии -->
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/switchList.js"></script>
    <script type="application/javascript" src="/js/openReview.js"></script>
</#macro>
<@page/>