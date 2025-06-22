package itis.animediary.controller;

import itis.animediary.utils.properties.RequestParameterNames;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ThemeController {

    @PostMapping("/set-theme")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setTheme(
            @RequestParam(RequestParameterNames.THEME) String theme,
            HttpSession session
    ) {
        session.setAttribute(RequestParameterNames.THEME, theme);
    }
}
