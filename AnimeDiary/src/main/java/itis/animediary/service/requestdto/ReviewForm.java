package itis.animediary.service.requestdto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ReviewForm {

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 10, message = "Оценка не может быть больше 10")
    private Short rateDrawing;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 10, message = "Оценка не может быть больше 10")
    private Short ratePlot;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 10, message = "Оценка не может быть больше 10")
    private Short rateCharacters;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 0, message = "Оценка не может быть меньше 0")
    @Max(value = 10, message = "Оценка не может быть больше 10")
    private Short rateOpening;

    @Length(max = 3500)
    private String reviewText;
}
