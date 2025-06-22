package itis.animediary.service.requestdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
public class CreateAnimeForm {

    @NotBlank(message = "Название не может быть пустым")
    private String name;

    private MultipartFile posterPhoto;

    @NotNull(message = "Поле \"Кол-во эпизодов\" не должно быть пустым")
    @Min(value = 0, message = "Количество эпизодов не может быть меньше 0")
    private Short numberOfEpisodes;

    @Min(value = 1917, message = "Год выпуска аниме не может быть меньше 1917")
    private Short year;

    @NotBlank(message = "Синопсис не должен быть пустым")
    private String synopsis;

    private String linkToWatch;

    @NotBlank(message = "Статус аниме не должен быть пустым")
    private String animeStatus;

    @NotBlank(message = "Тип аниме не должен быть пустым")
    private String animeType;

    private Set<String> genres;

    private Set<String> studios;
}
