<#include "base.ftl">

<#macro title>
    Forum
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_forum_page.css<#else>/css/light_theme/forum_page.css</#if>">
</#macro>

<#macro content>
    <h1 class="header-page-custom exo-2-header">Обсуждение аниме ${anime.getName()}</h1>

    <div class="d-flex flex-column comments-custom">
        <#if comments?size == 0>
            <h5 class="message-empty-list-custom">В обсуждении нет ни одного сообщения. Начните диалог!</h5>
        </#if>
        <#list comments as comment>
            <div class="card d-inline-flex mb-3">
                <div class="card-body background-card-custom">
                    <div class="d-flex justify-content-between mb-2">
                        <p class="card-title"><b>${comment.getUser().getNickname()}</b></p>
                        <p class="card-subtitle">${comment.getCommentDate()?datetime}</p>
                    </div>
                    <p class="card-text">${comment.getCommentText()}</p>
                </div>
            </div>
        </#list>
    </div>

    <div class="fixed-bottom background-custom">
        <#if errors?has_content>
            <#list errors as error>
                <div class="d-flex error-custom justify-content-center mb-3">
                    <p>${error}</p>
                </div>
            </#list>
        </#if>
        <div id="text_error"></div>
        <form action="/anime-info-page/${anime.getId()}/forum-page" method="POST" onsubmit="return validateComment()">
            <div class="d-flex comment-group-custom">
                <@csrf/>
                <textarea id="comment_text" name="commentText" class="comment-custom me-2" placeholder="Оставьте комментарий..."></textarea>
                <button class="btn btn-primary-custom" type="submit">
                    <span class="icon-send-custom">
                        <i class="fa-solid fa-paper-plane"></i>
                    </span>
                </button>
            </div>
        </form>
    </div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/validateComment.js"></script>
</#macro>

<@page/>
