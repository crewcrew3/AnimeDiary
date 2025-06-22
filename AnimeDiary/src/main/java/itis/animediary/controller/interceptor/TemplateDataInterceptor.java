package itis.animediary.controller.interceptor;

import itis.animediary.utils.helpers.CheckAuthorizationHelper;
import itis.animediary.utils.helpers.CheckRoleAdminHelper;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import itis.animediary.utils.properties.RequestParameterNames;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TemplateDataInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            ModelAndView modelAndView
    ) {
        //если нет значения темы в сессии, то по умолчанию ставим светлую
        if (request.getSession().getAttribute(RequestParameterNames.THEME) == null) {
            request.getSession().setAttribute(RequestParameterNames.THEME, "light");
        }
        // Добавляем данные в модель перед тем, как отобразится шаблон
        if (modelAndView != null) {
            ModelMap model = modelAndView.getModelMap();
            model.put(FreemarkerMapKeys.IS_AUTHORIZED, CheckAuthorizationHelper.isAuthorized());
            model.put(FreemarkerMapKeys.SESSION, request.getSession());
            model.put(FreemarkerMapKeys.IS_ADMIN, CheckRoleAdminHelper.isAdmin());

            CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (csrfToken != null) {
                model.put(FreemarkerMapKeys.CSRF, csrfToken);
            }
        }
    }
}

