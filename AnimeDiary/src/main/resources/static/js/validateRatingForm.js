function validateRatingForm() {
    let rd = $("#rate_drawing").val().trim() === "";
    let rp = $("#rate_plot").val().trim() === "";
    let rc = $("#rate_characters").val().trim() === "";
    let ro = $("#rate_opening").val().trim() === "";
    if (rd || rp || rc || ro) {
        alert("Заполните все поля с оценками!");
        return false;
    }
    return true;
}