<#include "base.ftl"/>

<#macro title>
    Create Anime
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_create_anime_page.css<#else>/css/light_theme/create_anime_page.css</#if>">
</#macro>

<#macro content>
    <div class="d-flex">
        <h1 class="header-page-custom exo-2-header mb-4">Создание аниме</h1>
        <div id="error_message_from_server" class="d-flex justify-content-end error-message-custom mb-4">
            <#if errors?has_content>
                <#list errors as error>
                    <div class="d-flex error-custom justify-content-center">
                        <p>${error}</p>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
    <form method="POST" action="/create-anime-page" onsubmit="return validateAnimeForm() && checkYear() && checkNumEp()" enctype="multipart/form-data">
        <div class="d-flex">
            <div class="d-flex flex-column container-poster-margin-custom">
                <div class="container-poster-custom mb-2">
                    <img src="/images/default_poster.jpg" class="image-poster-custom">
                </div>
                <p><input type="file" name="posterPhoto" accept=".jpg"/> </p>
            </div>

            <div class="d-flex flex-column container-inputs-custom">
                <div class="card background-card-custom">
                    <div class="card-body">
                        <@csrf/>
                        <label for="anime_name" class="form-label">Название аниме</label>
                        <input id="anime_name" name="name" type="text" class="form-control input-custom" placeholder="Название"/>

                        <label for="number_of_episodes" class="form-label mt-4">Количество эпизодов</label>
                        <input id="number_of_episodes" name="numberOfEpisodes" onblur="checkNumEp()" type="number" class="form-control input-custom" placeholder="Количество эпизодов"/>
                        <div id="message_number_of_episodes"></div>

                        <label for="year" class="form-label mt-4">Год (необязательно)</label>
                        <input id="year" name="year" onblur="checkYear()" type="number" title="Год должен быть не ранее 1917г. и не позденее текущего" class="form-control input-custom" placeholder="Год выпуска"/>
                        <div id="message_year"></div>

                        <label for="synopsis" class="form-label mt-4">Краткое описание</label>
                        <textarea id="synopsis" name="synopsis" type="text" class="form-control textarea-custom" placeholder="Синопсис"></textarea>

                        <label for="link_to_watch" class="form-label mt-4">Ссылка для просмотра (необязательно)</label>
                        <input id="link_to_watch" name="linkToWatch" type="text" class="form-control input-custom" placeholder="Ссылка"/>

                        <label for="anime_status" class="form-label mt-4">Статус</label>
                        <select id="anime_status" name="animeStatus" class="form-select input-custom form-select-custom">
                            <option>Онгоинг</option>
                            <option>Завершен</option>
                            <option>Приостановлен</option>
                            <option>Выпуск прекращен</option>
                        </select>

                        <label for="anime_type" class="form-label mt-4">Тип</label>
                        <select id="anime_type" name="animeType" class="form-select input-custom form-select-custom">
                            <option>TV-сериал</option>
                            <option>OVA</option>
                            <option>ONA</option>
                            <option>Фильм</option>
                            <option>Спешл</option>
                        </select>

                        <div class="form-group mt-4">
                            <label for="animeSelection"><b>Выберите жанры:</b></label>
                            <#list genres as genre>
                                <div class="form-check">
                                    <label for="genre${genre.getId()}">${genre.getName()}</label>
                                    <input id="genre${genre.getId()}" class="form-check-input" type="checkbox" name="genres" value="${genre.getName()}">
                                </div>
                            </#list>
                        </div>

                        <div class="form-group mt-4">
                            <label for="animeSelection"><b>Выберите студии:</b></label>
                            <#list studios as studio>
                                <div class="form-check">
                                    <label for="studio${studio.getId()}">${studio.getName()}</label>
                                    <input id="studio${studio.getId()}" class="form-check-input" type="checkbox" name="studios" value="${studio.getName()}">
                                </div>
                            </#list>
                        </div>
                        <button class="btn btn-primary-custom mt-4" type="submit">Сохранить</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/checkYear.js"></script>
    <script type="application/javascript" src="/js/checkNumEp.js"></script>
    <script type="application/javascript" src="/js/validateAnimeForm.js"></script>
</#macro>
<@page/>