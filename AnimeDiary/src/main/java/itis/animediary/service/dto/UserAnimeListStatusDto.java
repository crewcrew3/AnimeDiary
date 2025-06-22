package itis.animediary.service.dto;

import itis.animediary.service.dto.anime.AnimeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnimeListStatusDto {
    private long id;
    private AnimeDto anime;
    private UserDto user;
    private boolean inPlans;
    private boolean watching;
    private boolean watched;
    private boolean dropped;
    private boolean liked;
    private boolean disliked;
}
