package itis.animediary.controller.exceptionlogic;

import itis.animediary.service.exceptions.RedirectableException;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "error/error404";
    }

    @ExceptionHandler(RedirectableException.class)
    public String handleRedirectableException(
            RedirectableException ex,
            HttpSession session
    ) {
        session.setAttribute(FreemarkerMapKeys.ERROR_KEY, List.of(ex.getMessage()));
        return "redirect:" + ex.getRedirectUrl();
    }
}
