package itis.animediary.service.mappers;

import itis.animediary.data.entity.AnimeTypeEntity;
import itis.animediary.service.dto.AnimeTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimeTypeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    AnimeTypeDto toDTO(AnimeTypeEntity entity);
}
