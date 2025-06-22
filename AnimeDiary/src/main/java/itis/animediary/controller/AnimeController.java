package itis.animediary.controller;


import itis.animediary.service.*;
import itis.animediary.service.dto.AnimeGenreDto;
import itis.animediary.service.dto.AnimeReviewDto;
import itis.animediary.service.dto.UserDto;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.dto.anime.AnimeShortDto;
import itis.animediary.service.dto.studio.AnimeStudioShortDto;
import itis.animediary.service.requestdto.CreateAnimeForm;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import itis.animediary.utils.properties.RequestParameterNames;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final UserAnimeListService userAnimeListService;
    private final AnimeStudioService animeStudioService;
    private final AnimeGenreService animeGenreService;

    @GetMapping("/anime-catalog-page")
    public String showAnimeCatalog(Model model) {
        List<AnimeShortDto> animes = animeService.getAllAnimesShortDto();
        model.addAttribute(FreemarkerMapKeys.ANIME_LIST_KEY, animes);
        return "anime_catalog_page";
    }

    @GetMapping("/anime-info-page/{id}")
    //@PreAuthorize("isAuthenticated()") если я это поставлю то вся страница не будет доступна не авторизированному пользователю
    public String showAnimeInfo(
            @PathVariable("id") Long animeId,
            @AuthenticationPrincipal UserDetails currentUser,
            Model model
    ) {
        AnimeDto anime = animeService.getAnimeById(animeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<AnimeReviewDto> reviews = reviewService.getAllReviewsByAnimeId(animeId);

        if (currentUser != null) {
            UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
            long userId = user.getId();
            AnimeReviewDto userReview = reviewService.getReviewByAnimeIdAndUserId(animeId, userId).orElse(null);

            //удалим свою рецензию из списка всех рецензий
            if (userReview != null) {
                reviews.removeIf(review -> review.getId() == userReview.getId());
            }

            Boolean isInUserList = userAnimeListService.getUserAnimeListByAnimeIdAndUserId(animeId, userId).isPresent(); //проверяем, есть ли аниме хотя бы в одном списке пользователя. Если нет - пользователь не может написать рецензию
            model.addAttribute(FreemarkerMapKeys.USER_REVIEW_KEY, userReview);
            model.addAttribute(FreemarkerMapKeys.IS_IN_USER_LIST, isInUserList);
        }

        model.addAttribute(FreemarkerMapKeys.ANIME_REVIEWS_KEY, reviews);
        model.addAttribute(FreemarkerMapKeys.ANIME_KEY, anime);
        return "anime_info_page";
    }

    @PostMapping("/add-to-list")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToList(
            @RequestParam(name = RequestParameterNames.ANIME_ID) int animeID,
            @RequestParam(name = RequestParameterNames.ANIME_LIST_NAME, required = false) String valueListName,
            @RequestParam(name = RequestParameterNames.ANIME_SPECIAL_LIST_NAME, required = false) String valueSpecialListName,
            @AuthenticationPrincipal UserDetails currentUser
    ) {

        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        if (valueListName != null) {
            userAnimeListService.addAnimeInUserList(userId, animeID, valueListName, false);
        }

        if (valueSpecialListName != null) {
            userAnimeListService.addAnimeInUserList(userId, animeID, valueSpecialListName, true);
        }
    }

    @GetMapping("/anime-search")
    @ResponseBody
    public List<AnimeShortDto> searchAnimes(
            @RequestParam(name = RequestParameterNames.SEARCH_ANIME_INPUT) String searchInput
    ) {
        return animeService.getAnimesByMask(searchInput);
    }


    @GetMapping("/filter-animes")
    @ResponseBody
    public List<AnimeShortDto> filterAnimes(
            @RequestParam(name = RequestParameterNames.GENRES_IDS) List<Integer> genreIds
    ) {
        System.out.println(genreIds.toString());
        return animeService.findAnimesByGenres(genreIds);
    }


    @GetMapping("/search-page")
    public String showSearchPage(Model model) {
        List<AnimeGenreDto> genres = animeGenreService.getAllGenres();
        model.addAttribute(FreemarkerMapKeys.ANIME_GENRES_LIST_KEY, genres);
        return "search_page";
    }

    @GetMapping("/create-anime-page")
    @PreAuthorize("hasRole('ADMIN')")
    public String showCreateAnimePage(
            HttpSession session,
            Model model
    ) {
        List<String> errors = (List<String>) session.getAttribute(FreemarkerMapKeys.ERROR_KEY);
        if (errors != null) {
            model.addAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            session.removeAttribute(FreemarkerMapKeys.ERROR_KEY);
        }

        List<AnimeStudioShortDto> studios = animeStudioService.getAllStudiosShortDto();
        List<AnimeGenreDto> genres = animeGenreService.getAllGenres();

        model.addAttribute(FreemarkerMapKeys.STUDIOS, studios);
        model.addAttribute(FreemarkerMapKeys.ANIME_GENRES_LIST_KEY, genres);
        return "create_anime_page";
    }

    @PostMapping("/create-anime-page")
    @PreAuthorize("hasRole('ADMIN')")
    public String submitCreateAnimePage(
            @Valid CreateAnimeForm animeForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            session.setAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            return "redirect:/create-anime-page";
        }

        long animeId = (animeService.createAnime(animeForm)).getId();
        return "redirect:/anime-info-page/" + animeId;
    }
}
