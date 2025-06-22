package itis.animediary.controller;

import itis.animediary.controller.responsemodel.ResponseModel;
import itis.animediary.service.AnimeService;
import itis.animediary.service.ReviewService;
import itis.animediary.service.dto.AnimeReviewDto;
import itis.animediary.service.dto.AuthorityDto;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.dto.anime.AnimeShortDto;
import itis.animediary.service.requestdto.ProfileForm;
import itis.animediary.utils.properties.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import itis.animediary.service.dto.UserDto;
import itis.animediary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AnimeService animeService;
    private final ReviewService reviewService;

    @GetMapping("/managing-members")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMembersList(
            @AuthenticationPrincipal UserDetails currentUser,
            Model model
    ) {
        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        List<UserDto> members = userService.getAllUsers();

        //удалим себя из списка
        members.removeIf(member -> member.getId() == userId);

        model.addAttribute(FreemarkerMapKeys.MEMBERS, members);
        return "manage_members";
    }

    @PostMapping("/change-member-block-status")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseModel changeMemberBlockStatus(
            @RequestParam(name = RequestParameterNames.MEMBER_ID) Long memberId
    ) {
        ResponseModel responseBody = new ResponseModel();
        responseBody.getResponseMap().put(ResponseMapKeys.IS_NON_LOCKED, userService.changeMemberBlockStatus(memberId)); //true - если пользователь разблокирован
        return responseBody;
    }

    @PostMapping("/change-member-role")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseModel changeMemberRole(
            @RequestParam(name = RequestParameterNames.MEMBER_ID) Long memberId,
            @RequestParam(name = RequestParameterNames.ROLE_NAME) String role,
            @RequestParam(name = RequestParameterNames.IS_ASSIGN_ROLE) Boolean isAssignRole //если true - значит мы хотим присвоить роль, иначе - отобрать
    ) {
        ResponseModel responseBody = new ResponseModel();
        userService.changeMemberRole(memberId, role, isAssignRole);
        responseBody.getResponseMap().put(ResponseMapKeys.MEMBER_ROLE, role);
        responseBody.getResponseMap().put(ResponseMapKeys.ROLE_STATUS, isAssignRole);
        return responseBody;
    }

    @GetMapping("/profile-page")
    public String showProfilePage(
            @AuthenticationPrincipal UserDetails currentUser,
            Model model
    ) {
        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        String userRole = getUserRoleName(user.getAuthorities());
        List<AnimeDto> animes = animeService.getUsersAnimes(user.getId());

        model.addAttribute(FreemarkerMapKeys.USER_ROLE_NAME_KEY, userRole);
        model.addAttribute(FreemarkerMapKeys.USER_KEY, user);
        model.addAttribute(FreemarkerMapKeys.ANIME_LIST_KEY, animes);
        return "profile_page";
    }

    @GetMapping("/profile-page/edit")
    public String showEditProfilePage(
            @AuthenticationPrincipal UserDetails currentUser,
            HttpSession session,
            Model model
    ) {
        List<String> errors = (List<String>) session.getAttribute(FreemarkerMapKeys.ERROR_KEY);
        if (errors != null) {
            model.addAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            session.removeAttribute(FreemarkerMapKeys.ERROR_KEY);
        }
        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        model.addAttribute(FreemarkerMapKeys.USER_KEY, user);
        return "edit_profile";
    }

    @PostMapping("/profile-page/edit")
    public String submitProfileForm(
            @AuthenticationPrincipal UserDetails currentUser,
            @Valid ProfileForm profileForm,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            session.setAttribute(FreemarkerMapKeys.ERROR_KEY, errors);
            return "redirect:/profile-page/edit";
        }

        UserDto user = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        long userId = user.getId();
        userService.updateUserProfile(userId, profileForm);
        return "redirect:/profile-page";
    }

    private static String getUserRoleName(Set<AuthorityDto> authorities) {
        boolean isAdmin = authorities.stream()
                .anyMatch(auth -> "ROLE_ADMIN".equals(auth.getName()));
        return isAdmin ? "Администратор" : "Пользователь";
    }

    @GetMapping("/get-reviews-for-profile")
    @ResponseBody
    public ResponseModel getReviewsForProfile(
            @RequestParam(name = RequestParameterNames.ANIME_ID) String animeIdStr,
            @AuthenticationPrincipal UserDetails currentUser
    ) {
        long animeId = Long.parseLong(animeIdStr);
        long userId = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)).getId();

        AnimeShortDto anime = animeService.getAnimeShortById(animeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AnimeReviewDto review = reviewService.getReviewByAnimeIdAndUserId(animeId, userId).orElse(null);

        ResponseModel responseBody = new ResponseModel();
        responseBody.getResponseMap().put(ResponseMapKeys.RESULT_REVIEW, review);
        responseBody.getResponseMap().put(ResponseMapKeys.RESULT_ANIME, anime);

        return responseBody;
    }

    @GetMapping("/switch-list")
    @ResponseBody
    public List<AnimeDto> getAnimesByListName(
        @RequestParam(name = RequestParameterNames.SWITCH_LIST) String listName,
        @AuthenticationPrincipal UserDetails currentUser
    ) {
        long userId = userService.getUserByEmail(currentUser.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)).getId();

        return animeService.getAnimesByListName(listName, userId);
    }
}
