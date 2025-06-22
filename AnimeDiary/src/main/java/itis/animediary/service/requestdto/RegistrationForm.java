package itis.animediary.service.requestdto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegistrationForm {

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

