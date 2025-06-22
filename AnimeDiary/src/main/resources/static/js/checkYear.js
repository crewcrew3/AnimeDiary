function checkYear() {
    let regex = /^[1-9]\d*$/;
    let year = $("#year").val();
    let flag = (regex.test(year) && year >= 1917 && year <= new Date().getFullYear()) || year.trim() == "";

    if (flag) {
        $("#message_year").html("");
        return true;
    } else {
        $("#message_year").html("<div class='error-custom text-center'><p>Введите корректную дату (не ранее 1917г. и не позденее текущего)</p></div>");
        return false;
    }
}