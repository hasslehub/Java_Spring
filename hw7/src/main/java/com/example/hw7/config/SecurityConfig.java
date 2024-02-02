package com.example.hw7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String LOGIN = "/login";

    // цепочка фильтров
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("**", "/", LOGIN, "/logout").permitAll() // разрешение всем на вход стартовй страницы
                        .requestMatchers("/public-data").authenticated()              // разрешение к ресурсу /public-data для всех аутентифицированных пользователей
                        .requestMatchers("/private-data").hasAnyRole("ADMIN")            // разрешение к ресурсу /private-data только для аутентифицированных пользователей с ролью ADMIN
                        .anyRequest().authenticated()   // ограничение доступа для любого другого ресурса, отличного от /, (пользователь должен пройти проверку подлинности)
                )

                .formLogin(login -> login
                        .loginPage(LOGIN)
                        .failureUrl(LOGIN + "?error=true")
                        .defaultSuccessUrl("/")
                        .permitAll())

                .logout(logout -> logout
                        .logoutSuccessUrl(LOGIN)
                        .invalidateHttpSession(true)
                        .permitAll())
                .exceptionHandling(exception -> exception.accessDeniedPage(LOGIN));
        return http.build();
    }

    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        // добавляем двух пользователей (хранение в памяти)
        var user1 = User.withUsername("user")                  // пользователь user
                .password(bCrypt().encode("user")) // пароль для пользователя user с шифрованием
                .roles("USER")                                 // роль пользователя
                .build();

        var user2 = User.withUsername("admin")                 // пользователь admin
                .password(bCrypt().encode("admin")) // пароль для пользователя admin с шифрованием
                .roles("USER", "ADMIN")                         // роль пользователя
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    // Создаем кодировщик пароля с параметром надежности 10 по умолчанию
    @Bean
    public BCryptPasswordEncoder bCrypt() {
        return new BCryptPasswordEncoder();
    }
}
