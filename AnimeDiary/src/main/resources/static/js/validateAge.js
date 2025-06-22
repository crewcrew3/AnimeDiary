function validateAge() {
    let regex = /^0$|^[1-9]\d*$/;
    let age = $("#user_age").val();
    let ageMsg = $("#message_age_input");

    let ageFlag = regex.test(age) || age.trim() == "";

    if (!ageFlag) {
        ageMsg.html("<div class='container error-text-custom'><p>Возраст некорректен</p></div>");
        return false;
    }
    ageMsg.html("");
    return true;
}