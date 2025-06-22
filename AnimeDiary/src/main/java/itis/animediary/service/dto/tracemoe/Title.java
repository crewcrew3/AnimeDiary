package itis.animediary.service.dto.tracemoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Title {
    @JsonProperty("native")
    private String nativeTitle;
    @JsonProperty("romaji")
    private String romaji;
    @JsonProperty("english")
    private String english;
}
