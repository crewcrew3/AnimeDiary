function switchList(listName) {

    $(".list-group-item").click(function() {
        // Удаляем класс active-custom у всех элементов
        $(".list-group-item").removeClass("active-custom");
        // Добавляем класс active-custom к текущему элементу
        $(this).addClass("active-custom");
    });

    $("#list_of_animes").html(""); //чистим все в блоке перед тем как обобразить новое
    $.ajax({
        url: "/switch-list",
        data: {
            "switch_to_list_name": listName
        },
        success: function (animes) {
            let result = $("#list_of_animes");
            if (animes.length == 0) {
                result.append("<h5 class='message-empty-list-custom'> Вы пока что ничего не добавили в этот список. </h5>");
            } else {
                animes.forEach((anime) => {
                    result.append(
                        "<div class='card card-anime-review-custom mb-3'>" +
                        "<div class='row g-0'>" +
                        "<div class='col-3'>" +
                        "<img src='/images/" + anime.posterImgPath +" ' class='img-fluid rounded image-poster-custom mb-4' alt='Постер'>" +
                        "</div>" +
                        "<div class='col-9'>" +
                        "<div class='card-body'>" +
                        "<h5 class='card-title exo-2-header mb-3'>" + anime.name + "</h5>" +
                        anime.synopsis +
                        "<div class='d-flex justify-content-end'>" +
                        "<button onclick='openReview(" + anime.id + ")' class='btn btn-secondary-custom mb-3 mt-3 me-3' type='button' data-bs-toggle='modal' data-bs-target='#review_modal'> Посмотреть рецензию </button>" +
                        "<a class='btn btn-primary-custom mb-3 mt-3' href='anime-info-page/" + anime.id + " '>Подробнее</a>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "<hr>"
                    );
                });
            }
        },
        error: function (animes) {
            alert("Возникла ошибка при смене списка");
        }
    });
}