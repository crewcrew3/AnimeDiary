package itis.animediary.controller;

import itis.animediary.service.AnimeService;
import itis.animediary.service.CommentOnForumService;
import itis.animediary.service.UserService;
import itis.animediary.service.dto.CommentOnForumDto;
import itis.animediary.service.dto.UserDto;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.requestdto.CommentForm;
import itis.animediary.utils.properties.FreemarkerMapKeys;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ForumController {

    private final AnimeService animeService;
    private final UserService userService;
    private final CommentOnForumService commentOnForumService;

    @GetMapping("/anime-info-page/{id}/forum-page")
    public String showForumPage(
            @PathVariable("id") Long animeId,
            HttpSession session,
            Model model
    ) {
        AnimeDto anime = animeService.getAnimeById(animeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<String> errors = (List<String>) session.getAttribute(FreemarkerMapKeys.ERROR_KEY);
        if (errors != null) {
            model.addAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            session.removeAttribute(FreemarkerMapKeys.ERROR_KEY);
        }

        List<CommentOnForumDto> comments = commentOnForumService.getAllCommentByAnimeId(animeId);

        model.addAttribute(FreemarkerMapKeys.ANIME_KEY, anime);
        model.addAttribute(FreemarkerMapKeys.COMMENT_LIST_KEY, comments);
        return "forum_page";
    }

    @PostMapping("/anime-info-page/{id}/forum-page")
    public String submitForum(
            @PathVariable("id") Long animeId,
            @AuthenticationPrincipal UserDetails currentUser,
            @Valid CommentForm commentForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            session.setAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            return "redirect:/anime-info-page/{id}/forum-page";
        }
        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        commentOnForumService.saveCommentOnForum(userId, animeId, commentForm);
        return "redirect:/anime-info-page/{id}/forum-page";
    }

}
