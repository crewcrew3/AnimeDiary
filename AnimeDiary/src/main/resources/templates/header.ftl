<#macro header>
    <div class="csrf-token-block">
        <@csrf/>
    </div>
    <nav class="navbar navbar-expand-lg nav-container-custom fixed-top">
        <div class="container-fluid">
            <span class="navbar-brand header-site-custom reggae-one-regular">Anime Diary</span>
            <!-- Как я поняла, это чтобы в мобильной версии меню нормально раскрывалось. Мне страшно это убирать, поэтому оставила -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar_supported_content" aria-controls="navbar_supported_content" aria-expanded="false" aria-label="Переключатель навигации">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- -->
            <div id="navbar_supported_content" class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto"> <!-- ul - тег для создания ненумерованного списка -->
                    <li class="nav-item"> <!-- li - тег для создания list item -->
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/main-page">Главная</a> <!-- a - тег для создания гиперссылки -->
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/anime-catalog-page">Каталог Аниме</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/profile-page">Профиль</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/studio-catalog-page">Каталог студий</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/search-page">Поиск аниме</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <div class="nav-item-container-custom">
                            <a class="nav-link nav-item-custom" href="/anime-search-by-pic">Найти аниме по картинке</a>
                        </div>
                    </li>
                </ul>

                <div class="d-flex">
                    <#if is_admin>
                        <div class="d-flex">
                            <a class="btn me-2 btn-primary-custom" href="/managing-members" role="button">Управление пользователями</a>
                        </div>
                    </#if>
                    <div class="dropdown me-2">
                        <button class="btn btn-third-custom dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Выбрать тему
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-item <#if theme == 'light'>active-custom</#if>" onclick="changeTheme('light')">Светлая</li>
                            <li class="dropdown-item <#if theme == 'dark'>active-custom</#if>" onclick="changeTheme('dark')">Тёмная</li>
                        </ul>
                    </div>

                    <#if is_authorized == true>
                        <form action="/sign-out-page" method="POST">
                            <button class="btn btn-secondary-custom" type="submit">Выйти</button>
                            <@csrf/>
                        </form>
                    <#else>
                        <div class="d-flex">
                            <a class="btn me-2 btn-primary-custom" href="/sign-in-page" role="button">Войти</a>
                            <a class="btn btn-secondary-custom" href="/sign-up-page" role="button">Зарегистрироваться</a>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    </nav>
</#macro>