package itis.animediary.service;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.UserAnimeListStatusEntity;
import itis.animediary.data.entity.UserEntity;
import itis.animediary.data.repository.AnimeRepository;
import itis.animediary.data.repository.UserAnimeListStatusRepository;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.service.dto.UserAnimeListStatusDto;
import itis.animediary.service.mappers.UserAnimeListStatusMapper;
import itis.animediary.utils.properties.ExceptionMessages;
import itis.animediary.utils.properties.OtherProperties;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAnimeListService {

    private final UserAnimeListStatusRepository userAnimeListStatusRepository;
    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;

    private final UserAnimeListStatusMapper userAnimeListStatusMapper;

    public Optional<UserAnimeListStatusDto> getUserAnimeListByAnimeIdAndUserId(long animeId, long userId) {
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));

        return userAnimeListStatusRepository.findByAnimeAndUser(anime, user).map(userAnimeListStatusMapper::toDTO);
    }

    public void addAnimeInUserList(long userId, long animeId, String listName, boolean isSpecialList) {
        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        Optional<UserAnimeListStatusEntity> existingOptional = userAnimeListStatusRepository.findByAnimeAndUser(anime, user);
        //если у юзера уже есть запись о том, в какие списки он добавил аниме, то обновляем ее.
        //При этом обновляем список любимое/нелюбимое и список со статусом просмотра раздельно.
        if (existingOptional.isPresent()) {
            UserAnimeListStatusEntity existing = getUpdatedListStatus(listName, isSpecialList, existingOptional);
            userAnimeListStatusRepository.save(existing);
        } else { //тут уже без разницы в какой список добавил пользователь. (т.к. даже если это был список "Любимое" у всех списков из группы статуса просмотра будет false)
            UserAnimeListStatusEntity newListStatus = UserAnimeListStatusEntity.builder()
                    .anime(anime)
                    .user(user)
                    .inPlans(listName.equals(OtherProperties.IN_PLANS))
                    .watching(listName.equals(OtherProperties.WATCHING))
                    .watched(listName.equals(OtherProperties.WATCHED))
                    .dropped(listName.equals(OtherProperties.DROPPED))
                    .liked(listName.equals(OtherProperties.LIKED))
                    .disliked(listName.equals(OtherProperties.DISLIKED))
                    .build();
            userAnimeListStatusRepository.save(newListStatus);
        }
    }

    private static UserAnimeListStatusEntity getUpdatedListStatus(String listName, boolean isSpecialList, Optional<UserAnimeListStatusEntity> existingOptional) {
        UserAnimeListStatusEntity existing = existingOptional.get();
        if (isSpecialList) {
            existing.setLiked(listName.equals(OtherProperties.LIKED));
            existing.setDisliked(listName.equals(OtherProperties.DISLIKED));
        } else {
            existing.setInPlans(listName.equals(OtherProperties.IN_PLANS));
            existing.setWatching(listName.equals(OtherProperties.WATCHING));
            existing.setWatched(listName.equals(OtherProperties.WATCHED));
            existing.setDropped(listName.equals(OtherProperties.DROPPED));
        }
        return existing;
    }
}
