package itis.animediary.service.mappers.comment;

import itis.animediary.data.entity.CommentOnForumEntity;
import itis.animediary.service.dto.CommentOnForumDto;
import itis.animediary.service.mappers.anime.AnimeMapper;
import itis.animediary.service.mappers.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimeMapper.class, UserMapper.class})
public interface CommentOnForumMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "anime", target = "anime")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "commentDate", target = "commentDate")
    @Mapping(source = "commentText", target = "commentText")
    CommentOnForumDto toDTO(CommentOnForumEntity entity);
}
