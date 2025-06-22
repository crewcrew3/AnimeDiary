package itis.animediary.service.dto.tracemoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AnilistInfo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idMal")
    private Integer idMal;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("synonyms")
    private List<String> synonyms;
    @JsonProperty("isAdult")
    private Boolean isAdult;
}
