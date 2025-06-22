function addToList(status, animeId) {

    const csrfToken = $("#csrfToken").val();

    $(".dropdown-item-list").click(function () {
        $(".dropdown-item-list").removeClass("active-custom");
        $(this).addClass("active-custom");
    });

    $.ajax({
        type: "POST",
        url: "/add-to-list",
        data: {
            "anime_list_name": status,
            "anime_id": animeId
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function () {
            showNotification("<p class='notification-custom'>Вы добавили аниме в список " + status + "! Написать или отредактировать свою рецензию вы можете <a href='/anime-info-page/" + animeId + "/review-page'> здесь.</a></p>");
            $("#list").text(status);
            $("#review_btn").html("<a class='btn btn-primary-custom me-3' href='/anime-info-page/" + animeId + "/review-page'>Написать рецензию</a>");
        },
        error: function () {
            alert("Не удалось добавить аниме в список");
        }
    });
}