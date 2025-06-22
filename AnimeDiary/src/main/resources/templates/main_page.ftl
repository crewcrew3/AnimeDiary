<#include "base.ftl"/>

<#macro title>
    Main Page
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_main_page.css<#else>/css/light_theme/main_page.css</#if>">
</#macro>

<#macro content>

    <div class="carousel-container-custom">
        <div id="carouselExampleAutoplaying" class="carousel slide carousel-custom" data-bs-ride="carousel">
            <div class="carousel-inner">
                <#list banners as banner>
                    <div class="carousel-item <#if banner_index == 0>active</#if>">
                        <img src="${banner}" class="d-block w-100 carousel-img-custom" alt="Баннер">
                    </div>
                </#list>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Предыдущий</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Следующий</span>
            </button>
        </div>
    </div>

    <div class="card background-card-custom">
        <div class="card-body">

            <div class="container-subtitle-custom">
                <h5 class="subtitle-custom text-center"><i>Вас когда-нибудь интересовал вопрос, как лучше всего отслеживать просмотренные вами аниме? Вы - заядлый отаку, который пересмотрел так много тайтлов, что голова идет кругом, или же совсем еще зеленый новичок, который кроме Тетради Смерти ничего не знает? В любом случае, раз вы здесь - значит, вы заинтересованы в искусстве японской мультипликации, а так же любите порядок и структурированность.</i></h5>
                <hr class="hr-custom">
            </div>

            <h1 class="title-custom reggae-one-regular">Встречайте AnimeDiary!</h1>

            <!-- Первый абзац -->
            <div class="d-flex justify-content-between paragraph-custom">
                <div class="text-container-custom">
                    <p class="text-left-custom">Познакомьтесь с <b>Anime Diary</b> – вашим личным дневником аниме, который поможет вам не только следить за тем, что вы смотрите, но и делиться своими впечатлениями с другими фанатами аниме. Это место, где ваши увлечения обретут новую жизнь!</p>
                </div>
                <div class="pic-container-custom">
                    <img src="https://i.playground.ru/p/mRxNtTg2y2rV6cLo7OxF9w.gif" class="pic-custom" alt="картинка">
                </div>
            </div>

            <!-- Второй абзац -->
            <div class="d-flex justify-content-between paragraph-custom">
                <div class="pic-container-custom">
                    <img src="https://pibig.info/uploads/posts/2021-05/1620096875_29-pibig_info-p-dvulichnaya-sestryonka-umaru-art-anime-kra-33.jpg" class="pic-custom" alt="картинка">
                </div>
                <div class="text-container-custom">
                    <p class="text-right-custom ms-3"><b>Anime Diary</b> предлагает уникальную возможность организовать свои просмотры аниме в удобные списки: "В планах", "Смотрю", "Просмотрено", "Брошено", "Любимое" и "Плохое". Теперь вы сможете легко отслеживать, что уже посмотрели, что еще предстоит увидеть и какие серии вам не понравились. С помощью нашего сайта вы никогда не потеряете нить своего аниме-путешествия!</p>
                </div>
            </div>

            <!-- Третий абзац -->
            <div class="d-flex justify-content-center paragraph-custom bordered-paragraph-custom">
                <div class="text-container-custom">
                    <p class="text-center bordered-text-custom">Кроме того, наш сайт предоставляет базовую информацию об аниме и студиях, которые их создавали. Вы сможете узнать о любимых сериалах больше, чем когда-либо, включая информацию о создателях, жанрах и многом другом. Это идеальная возможность расширить свои знания и углубить понимание аниме-культуры.</p>
                </div>
            </div>

            <!-- Четвертый абзац -->
            <div class="d-flex justify-content-between paragraph-custom">
                <div class="text-container-custom">
                    <p class="text-left-custom">Но это еще не все! Каждый пользователь может писать развернутые отзывы на аниме, оценивая их по ключевым критериям: рисовка, сюжет, персонажи и опенинг. Ваши мнения важны, и <b>Anime Diary</b> предоставляет вам платформу, чтобы выразить свои мысли и впечатления. Делитесь своим опытом и помогайте другим пользователям находить лучшие аниме!</p>
                </div>
                <div class="pic-container-custom">
                    <img src="https://pa1.narvii.com/6741/2345cc75591f8045a6d0e142244e9de69383b1a9_hq.gif" class="pic-custom" alt="картинка">
                </div>
            </div>

            <!-- Пятый абзац -->
            <div class="d-flex justify-content-between paragraph-custom">
                <div class="pic-container-custom">
                    <img src="https://i.pinimg.com/736x/33/55/52/335552aa70dbb64e1f0a13e72d1d5ddc.jpg" class="pic-custom" alt="картинка">
                </div>
                <div class="text-container-custom">
                    <p class="text-right-custom"><b>Anime Diary</b> – это не только инструмент для отслеживания, но и сообщество, где вы можете познакомиться с единомышленниками. Откройте для себя форум, посвященный каждому аниме, где вы сможете обсудить детали сюжета, поделиться теорией или просто пообщаться с другими фанатами. Узнайте, что думают другие о вашем любимом аниме, и откройте для себя новые перспективы. Чтение рецензий может помочь вам понять, что вы могли пропустить, и сделать ваши просмотры еще более увлекательными.</p>
                </div>
            </div>

            <!-- Шестой абзац -->
            <div class="d-flex justify-content-between paragraph-custom">
                <div class="text-container-custom">
                    <p class="text-left-custom">Присоединяйтесь к <b>Anime Diary</b> и станьте частью растущего сообщества аниме-энтузиастов. Здесь каждый найдет что-то для себя: от новичков до опытных зрителей. Делитесь своими открытиями, обсуждайте новые серии и находите друзей, которые разделяют вашу страсть к аниме.</p>
                </div>
                <div class="pic-container-custom">
                    <img src="https://i.pinimg.com/originals/8d/11/0c/8d110c373d14ead4acf2e16375739771.gif" class="pic-custom" alt="картинка">
                </div>
            </div>

            <div class="bottom-pic-container-custom">
                <img src="https://i.pinimg.com/originals/da/40/3d/da403d7d1724938bb25d2bedd961fe08.jpg" class="pic-custom" alt="Последняя картинка">
            </div>
        </div>
    </div>
</#macro>

<#macro scripts></#macro>

<@page/>