function findAnimes() {
    let search = $("#search_main_page");

    $("#found-list").html(""); //удалям предыдущие результаты поиска перед тем, как отобразить новые

    if (search.val().length > 1) {
        $.ajax({
            url: "/anime-search", //оправляем ajax-запрос на /anime-search
            data: {
                "search_anime_input": search.val() //Создается объект с параметром search_anime_input, который содержит значение поля ввода.
            },
            success: function (animeList) { //Функция, если запрос успешен. Сюда прилетит список аниме в джисон файлике в качестве ответа на запрос.
                let result = $("#found-list");
                result.append("<h6 class='header-search-custom'>Результат поиска:</h6>"); //добавим заголовок и открывающийся тег списка
                animeList.forEach((anime) => {
                    result.append("<a href='/anime-info-page/" + anime.id + "' class='list-group-item list-group-item-action mb-2 list-item-custom'>" + anime.name + "</a>");
                });
            },
            error: function (animeList) {
                alert("Возникла ошибка при поиске");
            }
        });
    }
}