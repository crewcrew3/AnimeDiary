function changeMemberRole(memberId, role, isAssignRole) {
    const csrfToken = $("#csrfToken").val();
    $.ajax({
        type: "POST",
        url: "/change-member-role",
        data: {
            "member_id": memberId,
            "role_name": role,
            "is_assign_role": isAssignRole
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function (responseModel) {
            const role = responseModel.responseMap.memberRole;
            const roleStatus = responseModel.responseMap.roleStatus;

            if (role === 'ROLE_ADMIN') {
                const button = $("#admin_role_btn_" + memberId);
                if (roleStatus === true) {
                    button.text("Снять с должности");
                    button.removeClass("btn-secondary-custom");
                    button.addClass("btn-primary-custom");
                    button.off("click").on("click", function() {
                        changeMemberRole(memberId, 'ROLE_ADMIN', false);
                    });
                } else {
                    button.text("Назначить администратором");
                    button.removeClass("btn-primary-custom");
                    button.addClass("btn-secondary-custom");
                    button.off("click").on("click", function() {
                        changeMemberRole(memberId, 'ROLE_ADMIN', true);
                    });
                }
            }
        },
        error: function (responseModel) {
            alert("Не удалось изменить роль");
        }
    });
}