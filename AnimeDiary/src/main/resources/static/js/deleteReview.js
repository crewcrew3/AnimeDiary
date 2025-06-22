function deleteReview(reviewId, animeId, element) {

    const csrfToken = $("#csrfToken").val();

    $.ajax({
        type: "POST",
        url: "/anime-info-page/" + animeId + "/review-page/delete",
        data: {
            "review_id": reviewId
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function (responseModel) {
            $(element).closest('.user-review-custom').remove();
            $("#my_review").remove();
            $("#btn_edit_review").text("Написать рецензию");
            $("#ic_rating").html("<i id='ic_rating' class='fa-solid fa-star'>" + responseModel.responseMap.updatedAnimeRating + "</i>");
        },
        error: function (responseMap) {
            alert("Не удалось удалить рецензию");
        }
    });
}