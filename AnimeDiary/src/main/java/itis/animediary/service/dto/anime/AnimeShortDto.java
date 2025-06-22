package itis.animediary.service.dto.anime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeShortDto {
    private long id;
    private String name;
    private String posterImgPath;
}
