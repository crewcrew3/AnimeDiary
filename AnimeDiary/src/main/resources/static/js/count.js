function count() {
    const textarea = $("#review_text");
    const count = textarea.val().length;
    const charsLeft = $("#chars_left");
    charsLeft.text(count + "/3500");
    if (count > 3500) {
        textarea.val(textarea.val().substring(0, 3500));
        charsLeft.text("3500/3500");
        charsLeft.css("color", "red");
    } else {
        charsLeft.css("color", "black");
    }
}