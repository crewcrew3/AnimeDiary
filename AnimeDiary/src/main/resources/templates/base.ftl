<#include "header.ftl"/>
<#include "csrf_token.ftl"/>

<#macro content></#macro>
<#macro title></#macro>
<#macro style></#macro>
<#macro scripts></#macro>

<#macro page>
    <#assign theme = session.getAttribute("theme")?if_exists>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Exo+2:ital,wght@0,100..900;1,100..900&family=Neucha&family=Reggae+One&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_base.css<#else>/css/light_theme/base.css</#if>">
        <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_header.css<#else>/css/light_theme/header.css</#if>">
        <link rel="stylesheet" href="/css/fonts.css">
        <@style/>
        <title>
            <@title/>
        </title>
    </head>
    <body>
    <@header/>
        <@content/>
        <@scripts/>
        <script type="application/javascript" src="/js/changeTheme.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/f4434c329b.js" crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>