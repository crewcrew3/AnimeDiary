package itis.animediary.service.mappers.studio;

import itis.animediary.data.entity.AnimeStudioEntity;
import itis.animediary.service.dto.studio.AnimeStudioDto;
import itis.animediary.service.mappers.anime.AnimeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimeMapper.class})
public interface AnimeStudioMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "foundationYear", target = "foundationYear")
    @Mapping(source = "founderName", target = "founderName")
    @Mapping(source = "officialSite", target = "officialSite")
    @Mapping(source = "numberOfEmployees", target = "numberOfEmployees")
    @Mapping(source = "animes", target = "animes")
    AnimeStudioDto toDTO(AnimeStudioEntity entity);
}
