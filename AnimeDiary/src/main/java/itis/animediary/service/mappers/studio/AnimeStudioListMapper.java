package itis.animediary.service.mappers.studio;

import itis.animediary.data.entity.AnimeStudioEntity;
import itis.animediary.service.dto.studio.AnimeStudioDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeStudioMapper.class})
public interface AnimeStudioListMapper {
    List<AnimeStudioDto> toDtoList(List<AnimeStudioEntity> entities);
}
