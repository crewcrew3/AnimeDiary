package itis.animediary.service.dto.studio;

import itis.animediary.service.dto.anime.AnimeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeStudioDto {
    private long id;
    private String name;
    private Short foundationYear;
    private String founderName;
    private String officialSite;
    private Integer numberOfEmployees;
    private Set<AnimeDto> animes;
}
