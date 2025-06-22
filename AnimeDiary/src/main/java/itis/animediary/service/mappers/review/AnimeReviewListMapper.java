package itis.animediary.service.mappers.review;

import itis.animediary.data.entity.AnimeReviewEntity;
import itis.animediary.service.dto.AnimeReviewDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnimeReviewMapper.class})
public interface AnimeReviewListMapper {
    List<AnimeReviewDto> toDTO(List<AnimeReviewEntity> entities);
}
