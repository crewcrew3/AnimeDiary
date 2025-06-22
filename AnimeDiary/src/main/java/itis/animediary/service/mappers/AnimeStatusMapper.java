package itis.animediary.service.mappers;

import itis.animediary.data.entity.AnimeStatusEntity;
import itis.animediary.service.dto.AnimeStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimeStatusMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    AnimeStatusDto toDTO(AnimeStatusEntity entity);
}
