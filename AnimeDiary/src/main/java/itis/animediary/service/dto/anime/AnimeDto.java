package itis.animediary.service.dto.anime;

import itis.animediary.service.dto.AnimeGenreDto;
import itis.animediary.service.dto.AnimeStatusDto;
import itis.animediary.service.dto.studio.AnimeStudioShortDto;
import itis.animediary.service.dto.AnimeTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeDto {
    private long id;
    private String name;
    private String posterImgPath;
    private short numberOfEpisodes;
    private Short year;
    private double rating;
    private String synopsis;
    private String linkToWatch;
    private AnimeStatusDto status;
    private AnimeTypeDto type;
    private Set<AnimeGenreDto> genres;
    private Set<AnimeStudioShortDto> studios;
}
