function validateEmail() {
    let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    let mail = $("#user_email").val();
    let emailMsg = $("#message_email_input");

    if (!regex.test(mail)) {
        emailMsg.html("<div class='container error-text-custom'><p>e-mail некорректен</p></div>");
        return false;
    }
    emailMsg.html("");
    return true;
}
