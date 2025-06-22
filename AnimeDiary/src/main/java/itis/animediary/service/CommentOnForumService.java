package itis.animediary.service;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.CommentOnForumEntity;
import itis.animediary.data.entity.UserEntity;
import itis.animediary.data.repository.AnimeRepository;
import itis.animediary.data.repository.CommentOnForumRepository;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.service.dto.CommentOnForumDto;
import itis.animediary.service.mappers.comment.CommentOnForumListMapper;
import itis.animediary.service.requestdto.CommentForm;
import itis.animediary.utils.properties.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentOnForumService {

    private final CommentOnForumRepository commentOnForumRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    private final CommentOnForumListMapper commentOnForumListMapper;

    public List<CommentOnForumDto> getAllCommentByAnimeId(long id) {
        AnimeEntity anime = animeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<CommentOnForumEntity> comments = commentOnForumRepository.getAllByAnime(anime);
        return commentOnForumListMapper.toDTO(comments);
    }

    public void saveCommentOnForum(long userId, long animeId, CommentForm commentForm) {
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        CommentOnForumEntity commentOnForumEntity = CommentOnForumEntity.builder()
                .anime(anime)
                .user(user)
                .commentText(commentForm.getCommentText())
                .build();
        commentOnForumRepository.save(commentOnForumEntity);
    }
}
