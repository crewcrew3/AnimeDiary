package itis.animediary.service.exceptions;

import itis.animediary.utils.properties.ExceptionMessages;

public class FileException extends RedirectableException {

    public FileException(String message, String redirectUrl) {
        super(message, redirectUrl);
    }
    public FileException(String message, String redirectUrl, Throwable cause) {
        super(message, redirectUrl, cause);
    }

    public static FileException forCreateAnime() {
        return new FileException(
                ExceptionMessages.FILE_ERROR,
                "/create-anime-page"
        );
    }

    public static FileException forProfileEdit() {
        return new FileException(
                ExceptionMessages.FILE_ERROR,
                "/profile-page/edit"
        );
    }
}
