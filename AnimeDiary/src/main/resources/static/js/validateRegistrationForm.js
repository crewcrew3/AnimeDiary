function validateRegistrationForm() {
    let nickname = $("#user_nickname").val().trim() === "";
    let pass = $("#user_password").val().trim() === "";
    let email = $("#user_email").val().trim() === "";
    if (nickname || pass || email) {
        alert("Заполните все поля формы!");
        return false;
    }
    return true;
}