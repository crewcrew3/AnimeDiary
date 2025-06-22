function validateAnimeForm() {
    let an = $("#anime_name").val().trim() === "";
    let noe = $("#number_of_episodes").val().trim() === "";
    let s = $("#synopsis").val().trim() === "";
    if (an || noe || s) {
        alert("Заполните все необходимые поля!");
        return false;
    }
    return true;
}