function check() {
    let regex = /^[1-9]\d*$/;

    let rd = $("#rate_drawing").val();
    let rp = $("#rate_plot").val();
    let rc = $("#rate_characters").val();
    let ro = $("#rate_opening").val();

    let flag = ((regex.test(rd) && rd <= 10) || rd == 0) && ((regex.test(rp) && rp <= 10) || rp == 0) && ((regex.test(rc) && rc <= 10) || rc == 0) && ((regex.test(ro) && ro <= 10)|| ro == 0);

    if (flag) {
        $("#error_message").html("");
        return true;
    } else {
        $("#error_message").html("<div class='error-custom text-center'><p>Введите корректные данные, оценка должна быть целым числом от 0 до 10 включительно!</p></div>");
        return false;
    }
}