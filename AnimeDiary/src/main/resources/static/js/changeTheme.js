function changeTheme(theme) {
    const csrfToken = $("#csrfToken").val();
    $.ajax({
        type: "POST",
        url: "/set-theme",
        data: {
            "theme": theme
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("Не удалось сменить тему");
        }
    });
}