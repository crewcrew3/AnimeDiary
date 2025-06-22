function filterAnimes() {
    const checkedGenres = $('input[name="genres"]:checked').map(function() {
        return $(this).val();
    }).get();
    $.ajax({
        url: "/filter-animes",
        data: {
            "genreIds": checkedGenres
        },
        success: function (animeList) {
            $("#anime_tyan_pic").html("")
            let result = $("#filtered-animes");
            let html = "<div class='card background-card-custom'>" +
                "<div class='card-body'>" +
                "<div class='row row-cols-5 g-4'>";
            animeList.forEach((anime) => {
                html +=
                    "<div class='col'>" +
                    "  <div class='card card-custom'>" +
                    "    <div class='poster-container-custom'>" +
                    "      <img src='/images/" + anime.posterImgPath + "' class='card-img image-poster-custom' alt='Постер'>" +
                    "    </div>" +
                    "    <div class='overlay'></div>" +
                    "    <div class='card-img-overlay d-flex flex-column justify-content-end'>" +
                    "      <div class='container text-center'>" +
                    "        <h5 class='card-title anime-name-text-custom exo-2-header'>" + anime.name + "</h5>" +
                    "        <a class='btn btn-learn-more' href='/anime-info-page/" + anime.id + "'>Подробнее</a>" +
                    "      </div>" +
                    "    </div>" +
                    "  </div>" +
                    "</div>";
            });
            html += "</div></div></div>";

            result.html(html);
        },
        error: function () {
            alert("Не удалось отфильтровать аниме");
        }
    });
}