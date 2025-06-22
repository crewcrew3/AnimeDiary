package itis.animediary.service.security;

import itis.animediary.utils.properties.OtherProperties;
import itis.animediary.service.requestdto.RegistrationForm;
import itis.animediary.data.entity.AuthorityEntity;
import itis.animediary.data.entity.UserEntity;
import itis.animediary.data.repository.AuthorityRepository;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.service.exceptions.DuplicateEmailException;
import itis.animediary.utils.properties.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public void registerUser(RegistrationForm registrationForm) {
        if (userRepository.findByEmail(registrationForm.getEmail()).isPresent()) {
            throw DuplicateEmailException.forSignUp();
        }

        AuthorityEntity authority = authorityRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.AUTHORITY_NOT_FOUND_ERROR));

        UserEntity user = UserEntity.builder()
                .nickname(registrationForm.getNickname())
                .email(registrationForm.getEmail())
                .password(passwordEncoder.encode(registrationForm.getPassword()))
                .age(registrationForm.getAge())
                .build();
        user.addAuthority(authority);

        userRepository.save(user);
    }

    public void authenticateUser(
            String email,
            String password,
            HttpServletRequest request
    ) {

        UserDetailsImpl user = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(email);
        if (passwordEncoder.matches(password, user.getPassword())) {
            //Создаём токен аутентификации
            //user (principal) - объект UserDetails, содержащий информацию о пользователе
            //null (credentials) - пароль обычно обнуляется после успешной аутентификации
            //user.getAuthorities() - список прав (ролей) пользователя
            //Этот токен будет храниться в контексте безопасности и использоваться для проверки доступа
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities()
                    );

            //даем знать секурити что пользователь вошел в систему, передавая ему токен
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //true - создаёт новую сессию, если её не существует, false - возвращает существующую сессию или null
            //создаем сессию дабы хранить состояние аутентификации между запросами
            HttpSession session = request.getSession(true);

            //Сохраняет весь контекст безопасности (с токеном аутентификации) в сессию
            //Использует стандартный ключ SPRING_SECURITY_CONTEXT, который ожидает Spring Security
            //Позволяет Spring Security восстанавливать аутентификацию при последующих запросах
            //Без этого при новом запросе пользователь будет считаться неаутентифицированным
            //Spring Security автоматически проверяет этот атрибут сессии при каждом запросе
            session.setAttribute(OtherProperties.SPRING_SECURITY_CONTEXT, SecurityContextHolder.getContext());
        }
    }
}
