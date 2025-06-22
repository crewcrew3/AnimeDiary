package itis.animediary.service.mappers.comment;

import itis.animediary.data.entity.CommentOnForumEntity;
import itis.animediary.service.dto.CommentOnForumDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CommentOnForumMapper.class})
public interface CommentOnForumListMapper {
    List<CommentOnForumDto> toDTO(List<CommentOnForumEntity> entities);
}
