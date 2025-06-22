package itis.animediary.service.mappers.anime;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.service.dto.anime.AnimeShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimeShortMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "posterImgPath", target = "posterImgPath")
    AnimeShortDto toDTO(AnimeEntity entity);
}
