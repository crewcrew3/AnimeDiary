package itis.animediary.service.mappers.anime;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.service.dto.anime.AnimeShortDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeShortMapper.class})
public interface AnimeShortListMapper {
    List<AnimeShortDto> toDtoList(List<AnimeEntity> entities);
}
