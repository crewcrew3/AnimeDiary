package itis.animediary.service.mappers.studio;

import itis.animediary.data.entity.AnimeStudioEntity;
import itis.animediary.service.dto.studio.AnimeStudioShortDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeStudioShortMapper.class})
public interface AnimeStudioShortListMapper {
    List<AnimeStudioShortDto> toDtoList(List<AnimeStudioEntity> entities);
}
