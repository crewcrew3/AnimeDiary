package itis.animediary.service.exceptions;

import itis.animediary.utils.properties.ExceptionMessages;

public class DuplicateEmailException extends RedirectableException {

    public DuplicateEmailException(String message, String redirectUrl) {
        super(message, redirectUrl);
    }
    public DuplicateEmailException(String message, String redirectUrl, Throwable cause) {
        super(message, redirectUrl, cause);
    }

    public static DuplicateEmailException forSignUp() {
        return new DuplicateEmailException(
                ExceptionMessages.DUPLICATE_EMAIL,
                "/sign-up-page"
        );
    }

    public static DuplicateEmailException forProfileEdit() {
        return new DuplicateEmailException(
                ExceptionMessages.DUPLICATE_EMAIL,
                "/profile-page/edit"
        );
    }
}