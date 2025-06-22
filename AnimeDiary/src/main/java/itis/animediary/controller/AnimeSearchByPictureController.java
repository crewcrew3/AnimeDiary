package itis.animediary.controller;

import itis.animediary.service.TraceMoeService;
import itis.animediary.service.dto.tracemoe.AnimeResult;
import itis.animediary.service.dto.tracemoe.TraceMoeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnimeSearchByPictureController {


    private final TraceMoeService traceMoeService;

    @GetMapping("/anime-search-by-pic")
    public String showSearchForm() {
        return "anime_search_by_picture";
    }

    @PostMapping("/anime-search-by-pic")
    public String searchAnime(@RequestParam("imageUrl") String imageUrl, Model model) {
        try {
            TraceMoeResponse response = traceMoeService.searchAnimeByImageUrl(imageUrl);
            if (response.getResult() != null && !response.getResult().isEmpty()) {
                AnimeResult firstResult = response.getResult().get(0);
                model.addAttribute("nativeTitle", firstResult.getAnilist().getTitle().getNativeTitle());
                model.addAttribute("romajiTitle", firstResult.getAnilist().getTitle().getRomaji());
                model.addAttribute("englishTitle", firstResult.getAnilist().getTitle().getEnglish());
                model.addAttribute("similarity", firstResult.getSimilarity());
                model.addAttribute("imageUrl", imageUrl);
            }
            return "anime_results";

        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при поиске: " + e.getMessage());
            return "redirect:/anime-search-by-pic";
        }
    }
}
