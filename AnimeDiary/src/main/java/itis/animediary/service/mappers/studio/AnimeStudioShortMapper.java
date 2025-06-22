package itis.animediary.service.mappers.studio;

import itis.animediary.data.entity.AnimeStudioEntity;
import itis.animediary.service.dto.studio.AnimeStudioShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimeStudioShortMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    AnimeStudioShortDto toDTO(AnimeStudioEntity entity);
}
