package itis.animediary.controller;

import itis.animediary.service.requestdto.RegistrationForm;
import itis.animediary.service.security.SecurityService;
import itis.animediary.utils.properties.ExceptionCodes;
import itis.animediary.utils.properties.ExceptionMessages;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import itis.animediary.utils.properties.RequestParameterNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final SecurityService securityService;

    // Страница регистрации
    @GetMapping("/sign-up-page") //Параметр required = false означает, что параметр является необязательным.
    public String showSignUpPage(
            HttpServletRequest request,
            Model model,
            HttpSession session
    ) {
        List<String> errors = (List<String>) session.getAttribute(FreemarkerMapKeys.ERROR_KEY);
        if (errors != null) {
            model.addAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            session.removeAttribute(FreemarkerMapKeys.ERROR_KEY);
        }

        return "sign_up_page";
    }

    // @Valid используется для активации валидации на объекте registrationForm перед тем, как он будет передан в метод контроллера
    // BindingResult: Этот объект хранит информацию о том, есть ли ошибки валидации для объекта registrationForm.
    // Он не только позволяет проверить, есть ли ошибки, но и дает доступ к подробной информации об этих ошибках, включая конкретные поля, которые не прошли валидацию, и причины этих ошибок.
    @PostMapping("/sign-up-page")
    public String handleSignUp(
            @Valid RegistrationForm registrationForm,
            BindingResult bindingResult,
            //@RequestParam(value = RequestParameterNames.IS_REMEMBER_USER, required = false) Boolean rememberMe,
            HttpServletRequest request,
            HttpSession session
    ) {
        System.out.println("!!!");
        if (bindingResult.hasErrors()) {
            System.out.println("!!??");
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            session.setAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            System.out.println("!!!2");
            return "redirect:/sign-up-page";
        }
        System.out.println("!!!67235462");
        securityService.registerUser(registrationForm);
        securityService.authenticateUser(
                registrationForm.getEmail(),
                registrationForm.getPassword(),
                request
        );
        System.out.println("!!!3");
        return "redirect:/main-page";
    }

    // Страница входа
    @GetMapping( "/sign-in-page")
    public String showSignInPage(
            HttpServletRequest request,
            @RequestParam(value = RequestParameterNames.ERROR, required = false) String errorCode,
            Model model
    ) {
        if (errorCode != null) {
            List<String> errorList = getErrorList(errorCode);
            model.addAttribute(
                    FreemarkerMapKeys.ERROR_KEY,
                    errorList
            );
        }

        return "sign_in_page";
    }

    private static List<String> getErrorList(String errorCode) {
        List<String> errorList;
        switch (errorCode) {
            case ExceptionCodes.BAD_CREDENTIALS -> errorList = List.of(ExceptionMessages.BAD_CREDENTIALS);
            case ExceptionCodes.DISABLED_USER_ERROR -> errorList = List.of(ExceptionMessages.DISABLED_USER_ERROR);
            default -> errorList = List.of(ExceptionMessages.COMMON_ERROR);
        }
        return errorList;
    }
}
