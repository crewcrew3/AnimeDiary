package itis.animediary.service.mappers.genre;

import itis.animediary.data.entity.AnimeGenreEntity;
import itis.animediary.service.dto.AnimeGenreDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimeGenreMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    AnimeGenreDto toDTO(AnimeGenreEntity entity);
}
