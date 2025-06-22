package itis.animediary.conf;

import itis.animediary.service.security.handler.CustomAuthFailureHandler;
import itis.animediary.service.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //позволяет включить глобальную настройку для авторизации и проверки прав доступа на уровне методов в вашем приложении
// (prePostEnabled = true по дефолту: Этот параметр включает поддержку аннотаций @PreAuthorize и @PostAuthorize.)
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthFailureHandler failureHandler;

    //AuthenticationManagerBuilder auth используется для настройки, как и с помощью каких бинов будет происходить аутентификация в приложении.
    //В данном случае в качестве сервиса мы будем использовать userService(реализует UserDetailsService).
    //Он предоставляет данные о пользователе в виде UserDetails с помощщью метода loadUserByUsername()
    //В качестве алгоритма кодирования используется BCryptPasswordEncoder
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/main-page", "/anime-catalog-page", "/anime-info-page/*","/studio-catalog-page", "/studio-info-page/*", "/search-page", "/anime-search", "/set-theme", "/filter-animes", "/anime-search-by-pic", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/sign-up-page", "/sign-in-page").anonymous()
                        .requestMatchers("/anime-info-page/*/forum-page", "/anime-info-page/*/review-page").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/sign-in-page")
                        .failureHandler(failureHandler)
                        .defaultSuccessUrl("/main-page", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/sign-out-page")
                        .logoutSuccessUrl("/main-page")
                        .deleteCookies("JSESSIONID", "rememberMe")
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .key("COOKIE_KEY")
                        .tokenValiditySeconds(60 * 60 * 24 * 30) // Время жизни куки 30 дней
                        .rememberMeParameter("rememberMe") // Имя параметра в форме
                        .userDetailsService(userDetailsServiceImpl)
                )
                .sessionManagement(session -> session
                        .maximumSessions(-1) //отключаем ограничения кол-ва сессий(т.е. чтобы пользователь мог спокойно заходить с разных устройств и у него на каждом сохранялась своя сессия)
                        .sessionRegistry(sessionRegistry()) // Регистрируем SessionRegistry
                );
                //.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}