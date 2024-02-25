package com.example.WebTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности для веб-приложения.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Пользовательский сервис для аутентификации.
     *
     * @return UserDetailsService с предопределенными пользователями.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        return manager;
    }

    /**
     * Фильтр безопасности для обработки HTTP-запросов.
     *
     * @param http Конфигурация безопасности HTTP.
     * @return Фильтр безопасности для цепочки фильтров.
     * @throws Exception В случае ошибок конфигурации.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/tasks")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage(("/login"))
                );

        return http.build();
    }


}
