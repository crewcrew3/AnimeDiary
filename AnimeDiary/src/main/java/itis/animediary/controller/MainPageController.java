package itis.animediary.controller;

import itis.animediary.utils.CarouselListSingleton;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/main-page")
    public String getMainPage(Model model) {
        model.addAttribute(FreemarkerMapKeys.BANNERS, CarouselListSingleton.getBanners());
        System.out.println("!!!4");
        return "main_page";
    }
}
