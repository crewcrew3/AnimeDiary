<#include "base.ftl">
<#--<#import "csrf_token.ftl" as csrf>-->

<#macro title>
    Sign Up
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_sign_up_page.css<#else>/css/light_theme/sign_up_page.css</#if>">
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
                    <h1 class="exo-2-header mt-5 mb-3">Регистрация</h1>
                    <form id="registration_form" method="POST" class="d-flex flex-column align-items-center" onsubmit="return validateRegistrationForm() && validatePass() && validateEmail() && validateAge()">
                        <@csrf/>
                        <label for="user_nickname" class="form-label">Имя пользователя</label>
                        <input id="user_nickname" name="nickname" type="text" class="form-control input-custom" placeholder="Nickname"/>

                        <label for="user_email" class="form-label mt-4">Электронная почта</label>
                        <input id="user_email" name="email" type="email" class="form-control input-custom" onblur="validateEmail()" placeholder="e-mail"/>
                        <div id="message_email_input"></div>

                        <label for="user_password" class="form-label mt-4">Пароль</label>
                        <input id="user_password" name="password" type="password" class="form-control input-custom" onblur="validatePass()" title="Должны содержаться символы A-Z a-z 0-9 минимум 1 раз. Длина пароля должна быть не менее 5 символов." placeholder="Password"/>
                        <div id="message_password_input"></div>

                        <label for="user_age" class="form-label mt-4">Ваш возраст (необязательно)</label>
                        <input id="user_age" name="age" type="number" class="form-control input-custom" onblur="validateAge()" placeholder="Age"/>
                        <div id="message_age_input"></div>

                        <div class="form-check mb-4 mt-4 form-check-custom">
                            <input id="remember_me_sign_up" name="rememberMe" class="form-check-input me-2 form-check-custom" type="checkbox">
                            <label for="remember_me_sign_up" class="form-check-label">
                                Запомнить меня
                            </label>
                        </div>
                        <button class="btn btn-primary-custom" type="submit">Зарегистрироваться</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/validateRegistrationForm.js"></script>
    <script type="application/javascript" src="/js/validateEmail.js"></script>
    <script type="application/javascript" src="/js/validatePass.js"></script>
    <script type="application/javascript" src="/js/validateAge.js"></script>
</#macro>
<@page/>

