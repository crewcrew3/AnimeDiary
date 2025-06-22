package itis.animediary.service.security.handler;

import itis.animediary.utils.properties.ExceptionCodes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        String exceptionCode = resolveExceptionCode(exception);
        response.sendRedirect("/sign-in-page?error=" + exceptionCode);
    }

    private String resolveExceptionCode(AuthenticationException exception) {
        //Спринг оборачивает ошибку DisabledException в InternalAuthenticationServiceException поэтому мы берем cause
        if (exception.getCause() instanceof DisabledException) {
            return ExceptionCodes.DISABLED_USER_ERROR;
        } else if (exception instanceof BadCredentialsException) {
            return ExceptionCodes.BAD_CREDENTIALS;
        }
        return ExceptionCodes.COMMON_ERROR;
    }
}
