<#include "base.ftl">

<#macro title>
    Review
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_review_page.css<#else>/css/light_theme/review_page.css</#if>">
</#macro>

<#macro content>
    <form method="POST" action="/anime-info-page/${anime.getId()}/review-page" onsubmit="return validateRatingForm() && check()" class="d-flex flex-column form-container-custom">
        <h2 class="anime-title-custom exo-2-header mb-3">Рецензия на аниме "${anime.getName()}"</h2>
        <div class="d-flex">
            <div class="d-flex flex-column column-custom">
                <@csrf/>

                <#if user_review??>
                    <label for="rate_drawing" class="form-label">Оцените рисовку от 1 до 10</label>
                    <input id="rate_drawing" name="rateDrawing" value="${user_review.getRateDrawing()}" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_plot" class="form-label">Оцените сюжет от 1 до 10</label>
                    <input id="rate_plot" name="ratePlot" value="${user_review.getRatePlot()}" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_characters" class="form-label">Оцените персонажей от 1 до 10</label>
                    <input id="rate_characters" name="rateCharacters" value="${user_review.getRateCharacters()}" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_opening" class="form-label">Оцените опенинг от 1 до 10</label>
                    <input id="rate_opening" name="rateOpening" value="${user_review.getRateOpening()}" class="form-control input-custom mb-5" onblur="check()" type="number"/>

                <#else>
                    <label for="rate_drawing" class="form-label">Оцените рисовку от 1 до 10</label>
                    <input id="rate_drawing" name="rateDrawing" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_plot" class="form-label">Оцените сюжет от 1 до 10</label>
                    <input id="rate_plot" name="ratePlot" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_characters" class="form-label">Оцените персонажей от 1 до 10</label>
                    <input id="rate_characters" name="rateCharacters" class="form-control input-custom mb-3" onblur="check()" type="number"/>

                    <label for="rate_opening" class="form-label">Оцените опенинг от 1 до 10</label>
                    <input id="rate_opening" name="rateOpening" class="form-control input-custom mb-5" onblur="check()" type="number"/>
                </#if>
            </div>

            <div id="error_message" class="d-flex justify-content-end error-message-custom mt-4"></div>
            <div id="error_message_from_server" class="d-flex justify-content-end error-message-custom mt-4">
                <#if errors?has_content>
                    <#list errors as error>
                        <div class="d-flex error-custom justify-content-center mb-3">
                            <p>${error}</p>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <label for="review_text">Поделитесь своими впечатлениями</label>
        <#if user_review??>
            <textarea id="review_text" name="reviewText" class="textarea-custom mt-2" oninput="count()">${user_review.getReviewText()}</textarea>
            <span id="chars_left" class="mb-3">${user_review.getReviewText()?length}/3500</span>
        <#else>
            <textarea id="review_text" name="reviewText" class="textarea-custom mt-2" oninput="count()"></textarea>
            <span id="chars_left" class="mb-3">0/3500</span>
        </#if>

        <div class="d-flex justify-content-end">
            <button class="btn btn-primary-custom" type="submit">Сохранить</button>
        </div>
    </form>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/count.js"></script>
    <script type="application/javascript" src="/js/check.js"></script>
    <script type="application/javascript" src="/js/validateRatingForm.js"></script>
</#macro>
<@page/>