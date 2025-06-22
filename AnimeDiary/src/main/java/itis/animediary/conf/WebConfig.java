package itis.animediary.conf;

import itis.animediary.controller.interceptor.TemplateDataInterceptor;
import itis.animediary.utils.properties.OtherProperties;
import lombok.NonNull;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
@ComponentScan("itis.animediary.controller")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TemplateDataInterceptor())
                .addPathPatterns("/**");  // Перехватываем все запросы
    }

    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        //registry.addViewController("/search-page").setViewName("search_page");
    }

    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + OtherProperties.IMAGE_ROOT_PATH)
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());;
    }
}
