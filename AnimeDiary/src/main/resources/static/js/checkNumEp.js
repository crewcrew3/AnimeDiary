function checkNumEp() {
    let regex = /^0$|^[1-9]\d*$/;
    let noe = $("#number_of_episodes").val();
    let flag = regex.test(noe);

    if (flag) {
        $("#message_number_of_episodes").html("");
        return true;
    } else {
        $("#message_number_of_episodes").html("<div class='error-custom text-center'><p>Введите корректное число</p></div>");
        return false;
    }
}