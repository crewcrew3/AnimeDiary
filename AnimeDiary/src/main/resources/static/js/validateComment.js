function validateComment() {
    let textError = $("#text_error");

    if ($("#comment_text").val().trim() === "") {
        textError.html("<div class='container error-text-custom'><p>Вы не можете отправить пустое сообщение!</p></div>");
        return false;
    }
    textError.html("");
    return true;
}
