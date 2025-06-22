function validatePass() {
    let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/;
    let pass = $("#user_password").val();
    let passMsg = $("#message_password_input");

    if (!regex.test(pass)) {
        passMsg.html("<div class='container text-center error-text-custom'><p>Должны содержаться символы A-Z a-z 0-9 минимум 1 раз. Длина пароля должна быть не менее 5 символов.</p></div>");
        return false;
    }
    passMsg.html("");
    return true;
}