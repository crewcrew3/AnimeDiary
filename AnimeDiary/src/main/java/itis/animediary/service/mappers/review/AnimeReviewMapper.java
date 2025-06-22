package itis.animediary.service.mappers.review;

import itis.animediary.data.entity.AnimeReviewEntity;
import itis.animediary.service.dto.AnimeReviewDto;
import itis.animediary.service.mappers.anime.AnimeMapper;
import itis.animediary.service.mappers.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnimeMapper.class, UserMapper.class})
public interface AnimeReviewMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "anime", target = "anime")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "reviewDate", target = "reviewDate")
    @Mapping(source = "rateDrawing", target = "rateDrawing")
    @Mapping(source = "ratePlot", target = "ratePlot")
    @Mapping(source = "rateCharacters", target = "rateCharacters")
    @Mapping(source = "rateOpening", target = "rateOpening")
    @Mapping(source = "resultScore", target = "resultScore")
    @Mapping(source = "reviewText", target = "reviewText")
    AnimeReviewDto toDTO(AnimeReviewEntity entity);
}
