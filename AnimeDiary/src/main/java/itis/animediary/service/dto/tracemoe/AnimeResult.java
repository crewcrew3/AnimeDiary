package itis.animediary.service.dto.tracemoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AnimeResult {
    @JsonProperty("anilist")
    private AnilistInfo anilist;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("episode")
    private String episode;
    @JsonProperty("from")
    private Double from;
    @JsonProperty("to")
    private Double to;
    @JsonProperty("similarity")
    private Double similarity;
    @JsonProperty("video")
    private String video;
    @JsonProperty("image")
    private String image;
}
