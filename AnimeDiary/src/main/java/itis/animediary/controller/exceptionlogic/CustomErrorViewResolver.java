package itis.animediary.controller.exceptionlogic;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class CustomErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(
            HttpServletRequest request,
            HttpStatus status,
            Map<String, Object> model) {

        if (status == HttpStatus.NOT_FOUND) {
            return new ModelAndView("error/error404", model);
        } else if (status == HttpStatus.FORBIDDEN) {
            return new ModelAndView("error/error403", model);
        } else if (status == HttpStatus.BAD_REQUEST) {
            return new ModelAndView("error/error400", model);
        }
        return new ModelAndView("error/error", model);
    }
}

