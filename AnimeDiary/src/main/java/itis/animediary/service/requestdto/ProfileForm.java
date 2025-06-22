package itis.animediary.service.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileForm {

    private MultipartFile profilePhoto;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @Length(max = 30)
    @NotBlank(message = "Никнейм не может быть пустым")
    private String nickname;

    @NotBlank(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{5,}$", message = "Пароль должен содержать минимум 1 заглавную букву, 1 цифру и быть не менее 5 символов")
    private String password;

    @Min(value = 0, message = "Возраст не может быть меньше 0")
    private Short age;
}
