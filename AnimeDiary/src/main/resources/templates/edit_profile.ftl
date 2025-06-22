<#include "base.ftl">

<#macro title>
    Edit Profile
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_edit_profile.css<#else>/css/light_theme/edit_profile.css</#if>">
</#macro>

<#macro content>
    <div class="d-flex">
        <h1 class="header-page-custom exo-2-header mb-4">Редактирование профиля</h1>
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
    <#if user??>
        <form method="POST" action="/profile-page/edit" enctype="multipart/form-data" onsubmit="return validateForm() && validatePass() && validateEmail()">
            <div class="d-flex">
                <div class="d-flex flex-column container-avatar-custom">
                    <div class="img-container-custom mb-2">
                        <img src="/images/${user.getPhotoURL()}" class="image-avatar-custom">
                    </div>
                    <p><input type="file" name="profilePhoto" accept=".jpg"/> </p>
                </div>

                <div class="d-flex flex-column container-inputs-custom">
                    <div class="card background-card-custom">
                        <div class="card-body">
                            <@csrf/>
                            <label for="user_nickname" class="form-label">Имя пользователя</label>
                            <input id="user_nickname" name="nickname" value="${user.getNickname()}" type="text" class="form-control input-custom" placeholder="Nickname"/>

                            <label for="user_email" class="form-label mt-4">Электронная почта</label>
                            <input id="user_email" name="email" value="${user.getEmail()}" type="email" class="form-control input-custom" onblur="validateEmail()" placeholder="e-mail"/>
                            <div id="message_email_input"></div>

                            <label for="user_password" class="form-label mt-4">Пароль</label>
                            <input id="user_password" name="password" type="password" class="form-control input-custom" onblur="validatePass()" title="Должны содержаться символы A-Z a-z 0-9 минимум 1 раз. Длина пароля должна быть не менее 5 символов." placeholder="Введите новый пароль или подтвердите старый..."/>
                            <div id="message_password_input"></div>

                            <label for="user_age" class="form-label mt-4">Ваш возраст (необязательно)</label>
                            <#if user.getAge()??>
                                <input id="user_age" name="age" value="${user.getAge()}" type="number" min="0" class="form-control input-custom" placeholder="Age"/>
                            <#else>
                                <input id="user_age" name="age" type="number" min="0" class="form-control input-custom" placeholder="Age"/>
                            </#if>
                            <button class="btn btn-primary-custom mt-4" type="submit">Сохранить</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </#if>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/validateRegistrationForm.js"></script>
    <script type="application/javascript" src="/js/validateEmail.js"></script>
    <script type="application/javascript" src="/js/validatePass.js"></script>
</#macro>
<@page/>