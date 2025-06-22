<#include "base.ftl">

<#macro title>
    Sign In
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_sign_in_page.css<#else>/css/light_theme/sign_in_page.css</#if>">
</#macro>

<#macro content>
    <div class="d-flex justify-content-center">
        <div class="d-flex flex-column content-custom">
            <#if errors?has_content>
                <#list errors as error>
                    <div class="d-flex error-custom justify-content-center mb-3">
                        <p>${error}</p>
                    </div>
                </#list>
            </#if>
            <div class="card d-inline-flex background-card-custom">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center">
                        <h1 class="mt-5 exo-2-header mb-3">Вход</h1>
                        <form method="POST" class="d-flex flex-column align-items-center">
                           <@csrf/> <#-- вот тут обращаюсь к нему-->
                            <label for="user_email_sign_in" class="form-label">Логин</label>
                            <input id="user_email_sign_in" name="username" type="email" class="form-control mb-4 input-custom" placeholder="e-mail" required/>

                            <label for="user_password_sign_in" class="form-label">Пароль</label>
                            <input id="user_password_sign_in" name="password" type="password" class="form-control mb-2 input-custom" placeholder="Password" required/>

                            <p>Еще нет аккаунта? <a href="/sign-up-page">Зарегистрируйтесь!</a></p>
                            <div class="form-check mb-2">
                                <input id="remember_me_sign_in" name="rememberMe" class="form-check-input me-2 form-check-custom" type="checkbox">
                                <label class="form-check-label" for="remember_me_sign_in">
                                    Запомнить меня
                                </label>
                            </div>
                            <button class="btn btn-primary-custom" type="submit">Войти</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>

<@page/>

