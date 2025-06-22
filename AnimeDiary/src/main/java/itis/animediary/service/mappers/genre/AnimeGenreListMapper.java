package itis.animediary.service.mappers.genre;

import itis.animediary.data.entity.AnimeGenreEntity;
import itis.animediary.service.dto.AnimeGenreDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeGenreMapper.class})
public interface AnimeGenreListMapper {
    List<AnimeGenreDto> toDtoList(List<AnimeGenreEntity> entities);
}
