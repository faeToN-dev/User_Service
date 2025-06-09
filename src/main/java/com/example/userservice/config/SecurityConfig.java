// Оголошення пакету, в якому знаходиться клас SecurityConfig
// -- для Order Service 'package com.example.userservice.config;'
// -- для User Service 'package com.example.userservice.config;'
package com.example.userservice.config;
// Імпортує:
// -- анотацію Bean
import org.springframework.context.annotation.Bean;
// -- анотацію Configuration.
import org.springframework.context.annotation.Configuration;
// -- клас HttpSecurity, який використовується для налаштування
// безпеки веб-додатків на основі сервлетів.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// -- AbstractHttpConfigurer, який додає зручний базовий клас
// для екземплярів SecurityConfigurer, що працюють на HttpSecurity.
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// -- SecurityFilterChain, який використовується FilterChainProxy
// для визначення того, які екземпляри Spring Security Filter
// слід викликати для поточного запиту.
import org.springframework.security.web.SecurityFilterChain;
// Позначає клас як конфігураційний, тобто Spring оброблятиме його
// і створить відповідні Bean-компоненти.
import com.example.userservice.filter.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
// Оголошення публічного класу SecurityConfig,
// який визначає правила безпеки для веб-запитів.
public class SecurityConfig {

    // Оголошується приватне фінальне поле для JWT-фільтра,
    // який буде вставлятися у ланцюжок фільтрів.
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    // Конструктор із залежністю — Spring автоматично передасть екземпляр
    // JwtAuthenticationFilter сюди через ін’єкцію.
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Додається JWT-фільтр перед стандартним
                // UsernamePasswordAuthenticationFilter у ланцюжку фільтрів.
                // Це означає, що спочатку оброблятиметься JWT-токен,
                // і тільки потім — інша авторизація.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}