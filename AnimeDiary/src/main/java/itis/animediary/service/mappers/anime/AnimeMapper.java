package itis.animediary.service.mappers.anime;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.mappers.genre.AnimeGenreMapper;
import itis.animediary.service.mappers.AnimeStatusMapper;
import itis.animediary.service.mappers.studio.AnimeStudioShortMapper;
import itis.animediary.service.mappers.AnimeTypeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimeStatusMapper.class, AnimeTypeMapper.class, AnimeGenreMapper.class, AnimeStudioShortMapper.class})
public interface AnimeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "posterImgPath", target = "posterImgPath")
    @Mapping(source = "numberOfEpisodes", target = "numberOfEpisodes")
    @Mapping(source = "year", target = "year")
    @Mapping(source = "rating", target = "rating")
    @Mapping(source = "synopsis", target = "synopsis")
    @Mapping(source = "linkToWatch", target = "linkToWatch")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "genres", target = "genres")
    @Mapping(source = "studios", target = "studios")
    AnimeDto toDTO(AnimeEntity entity);
}
