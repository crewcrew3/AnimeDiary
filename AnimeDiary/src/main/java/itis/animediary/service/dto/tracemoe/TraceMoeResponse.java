package itis.animediary.service.dto.tracemoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TraceMoeResponse {
    @JsonProperty("frameCount")
    private Long frameCount;
    @JsonProperty("error")
    private String error;
    @JsonProperty("result")
    private List<AnimeResult> result;
}
