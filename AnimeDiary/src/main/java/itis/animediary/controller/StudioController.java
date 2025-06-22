package itis.animediary.controller;

import itis.animediary.service.AnimeStudioService;
import itis.animediary.service.dto.studio.AnimeStudioDto;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudioController {

    private final AnimeStudioService animeStudioService;

    @GetMapping("/studio-catalog-page")
    public String showStudioCatalog(Model model) {
        List<AnimeStudioDto> studios = animeStudioService.getAllStudios();
        model.addAttribute(FreemarkerMapKeys.STUDIOS, studios);
        return "studio_catalog_page";
    }

    @GetMapping("/studio-info-page/{id}")
    public String showStudioInfo(
            @PathVariable("id") Long id,
            Model model
    ) {
        AnimeStudioDto studio = animeStudioService.getStudioById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute(FreemarkerMapKeys.STUDIO_KEY, studio);
        return "studio_info_page";
    }
}
