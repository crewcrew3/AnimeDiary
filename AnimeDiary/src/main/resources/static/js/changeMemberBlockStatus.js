function changeMemberBlockStatus(memberId) {
    const csrfToken = $("#csrfToken").val();
    $.ajax({
        type: "POST",
        url: "/change-member-block-status",
        data: {
            "member_id": memberId,
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function (responseModel) {
            const isNonLocked = responseModel.responseMap.isNonLocked;
            const button = $("#block_btn_" + memberId);
            const statusText = $("#status_text_" + memberId);
            if (isNonLocked) {
                button.text("Заблокировать");
                button.removeClass("btn-secondary-custom");
                button.addClass("btn-primary-custom");

                statusText.text("Активен");
                statusText.removeClass("blocked-text");
                statusText.addClass("active-text");
            } else {
                button.text("Разблокировать");
                button.removeClass("btn-primary-custom");
                button.addClass("btn-secondary-custom");

                statusText.text("Заблокирован");
                statusText.removeClass("active-text");
                statusText.addClass("blocked-text")
            }
        },
        error: function () {
            alert("Не удалось изменить статус блокировки пользователя");
        }
    });
}