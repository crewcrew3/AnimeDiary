package itis.animediary.service.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @NotBlank(message = "Сообщение не может быть пустым!")
    private String commentText;
}
