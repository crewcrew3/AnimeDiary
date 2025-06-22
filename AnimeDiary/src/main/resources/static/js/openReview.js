function openReview(animeID) {
    $.ajax({
        url: "/get-reviews-for-profile",
        data: {
            "anime_id": animeID
        },
        success: function (responseModel) {

            const review = responseModel.responseMap.resultReview;
            const anime = responseModel.responseMap.resultAnime;

            let modalBody = $("#modal_body");
            let reviewButton = $("#review_button");
            let reviewTitle = $("#review_modal_title");

            reviewTitle.text("Рецензия на аниме " + anime.name);

            if (review == null) {
                modalBody.html("<h5 class='message-empty-list-custom'>Вы еще не написали рецензию к этому аниме</h5>");
                reviewButton.html("<a class='btn btn-primary-custom' href='/anime-info-page/" + anime.id + "/review-page'>Написать рецензию</a>");
            } else {
                modalBody.html(
                    "<p class='mb-2'><b>Рисовка:</b> " + review.rateDrawing + "/10</p>" +
                    "<p class='mb-2'><b>Сюжет:</b> " + review.ratePlot + "/10</p>" +
                    "<p class='mb-2'><b>Персонажи:</b> " + review.rateCharacters + "/10</p>" +
                    "<p class='mb-2'><b>Опенинг:</b> " + review.rateOpening + "/10</p>" +
                    "<p class='card-text'>" + review.reviewText + "</p>"
                );
                reviewButton.html("<a class='btn btn-primary-custom' href='/anime-info-page/" + anime.id + "/review-page'>Редактировать рецензию</a>");
            }
        },
        error: function (responseModel) {
            alert("Возникла ошибка при получении рецензии");
        }
    });
}