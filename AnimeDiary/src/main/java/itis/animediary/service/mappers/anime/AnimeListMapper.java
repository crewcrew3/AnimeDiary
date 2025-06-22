package itis.animediary.service.mappers.anime;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.service.dto.anime.AnimeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeMapper.class})
public interface AnimeListMapper {
    List<AnimeDto> toDtoList(List<AnimeEntity> entities);
}
