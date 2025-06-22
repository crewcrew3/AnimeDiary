package itis.animediary.service.dto;

import itis.animediary.service.dto.anime.AnimeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeReviewDto {
    private long id;
    private AnimeDto anime;
    private UserDto user;
    private Date reviewDate;
    private short rateDrawing;
    private short ratePlot;
    private short rateCharacters;
    private short rateOpening;
    private double resultScore;
    private String reviewText;
}
