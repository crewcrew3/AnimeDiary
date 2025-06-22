package itis.animediary.controller;

import itis.animediary.controller.responsemodel.ResponseModel;
import itis.animediary.service.AnimeService;
import itis.animediary.service.ReviewService;
import itis.animediary.service.UserService;
import itis.animediary.service.dto.AnimeReviewDto;
import itis.animediary.service.dto.UserDto;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.requestdto.ReviewForm;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import itis.animediary.utils.properties.RequestParameterNames;
import itis.animediary.utils.properties.ResponseMapKeys;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class ReviewController {

    private final AnimeService animeService;
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/anime-info-page/{id}/review-page")
    public String showReviewPage(
            @PathVariable("id") Long animeId,
            @AuthenticationPrincipal UserDetails currentUser,
            HttpSession session,
            Model model
    ) {
        AnimeDto anime = animeService.getAnimeById(animeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<String> errors = (List<String>) session.getAttribute(FreemarkerMapKeys.ERROR_KEY);
        if (errors != null) {
            model.addAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            session.removeAttribute(FreemarkerMapKeys.ERROR_KEY);
        }
        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        AnimeReviewDto userReview = reviewService.getReviewByAnimeIdAndUserId(animeId, userId).orElse(null);

        model.addAttribute(FreemarkerMapKeys.ANIME_KEY, anime);
        model.addAttribute(FreemarkerMapKeys.USER_REVIEW_KEY, userReview);

        return "review_page";
    }

    //можно было бы разделить на /review-page/new и /review-page/edit но как будто было бы дублирование кода..
    @PostMapping("/anime-info-page/{id}/review-page")
    public String submitReview(
            @PathVariable("id") Long animeId,
            @AuthenticationPrincipal UserDetails currentUser,
            @Valid ReviewForm reviewForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            session.setAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            return "redirect:/anime-info-page/{id}/review-page";
        }

        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        reviewService.saveOrUpdate(userId, animeId, reviewForm);
        animeService.updateAnimeRatingById(animeId);
        return "redirect:/anime-info-page/{id}";
    }

    @PostMapping("/anime-info-page/{id}/review-page/delete")
    @ResponseBody
    public ResponseModel deleteReview(
            @PathVariable("id") Long animeId,
            @RequestParam(name = RequestParameterNames.REVIEW_ID) String reviewIdStr
    ) {
        long reviewId = Long.parseLong(reviewIdStr);
        reviewService.deleteReview(reviewId);
        double updatedRating = animeService.updateAnimeRatingById(animeId);

        ResponseModel response = new ResponseModel();
        response.getResponseMap().put(ResponseMapKeys.UPDATED_ANIME_RATING, updatedRating);
        return response;
    }
}
