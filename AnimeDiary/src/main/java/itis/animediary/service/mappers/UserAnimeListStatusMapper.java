package itis.animediary.service.mappers;

import itis.animediary.data.entity.UserAnimeListStatusEntity;
import itis.animediary.service.dto.UserAnimeListStatusDto;
import itis.animediary.service.mappers.anime.AnimeMapper;
import itis.animediary.service.mappers.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimeMapper.class, UserMapper.class})
public interface UserAnimeListStatusMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "anime", target = "anime")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "inPlans", target = "inPlans")
    @Mapping(source = "watching", target = "watching")
    @Mapping(source = "watched", target = "watched")
    @Mapping(source = "dropped", target = "dropped")
    @Mapping(source = "liked", target = "liked")
    @Mapping(source = "disliked", target = "disliked")
    UserAnimeListStatusDto toDTO(UserAnimeListStatusEntity entity);
}
