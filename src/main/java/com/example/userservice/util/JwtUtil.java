// Оголошення пакету, в якому знаходиться клас JwtUtil
package com.example.userservice.util;
// Імпортуються класи для роботи з JWT (JSON Web Token):
// -- Claims — об'єкт, який містить дані (права) із токена.
import io.jsonwebtoken.Claims;
// -- Jwts — допоміжний клас для створення і розбору JWT.
import io.jsonwebtoken.Jwts;
// -- SignatureAlgorithm — алгоритми підпису токенів (наприклад, HS256).
import io.jsonwebtoken.SignatureAlgorithm;
// -- @Component — робить клас доступним для ін'єкції через Spring.
import org.springframework.stereotype.Component;
// -- Date використовується для встановлення часу створення і закінчення токена.
import java.util.Date;
// Клас JwtUtil оголошується як Spring-компонент,
// тобто буде автоматично керований контейнером Spring.
// Він відповідає за створення і перевірку JWT токенів.
@Component
public class JwtUtil {
    // Секретний ключ (SECRET_KEY), який використовується для підпису
    // і валідації токенів.
    // Важилво: у реальних додатках треба використовувати складніший
    // і безпечніший ключ.
    private final String SECRET_KEY = "9ACFD5FCD6C7DDF2BED1F94588E4B";
    // Метод generateToken створює новий токен для заданого імені користувача
    // (username) і ролі (role).
    public String generateToken(String username, String role) {
        // Починається побудова нового JWT токена.
        return Jwts.builder()
                // Встановлюється "subject" токена — ім'я користувача.
                .setSubject(username)
                // Додається власна claim (атрибут) — роль користувача (role).
                .claim("role", role)
                // Встановлюється час створення токена — поточний час.
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Встановлюється час закінчення дії токена — через 10 годин.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *
                        60 * 60 * 10))
                // Токен підписується алгоритмом HS256 і секретним ключем SECRET_KEY.
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // Завершується побудова токена і повертається
                // його компактне (рядкове) представлення.
                .compact();
    }
    // Метод extractClaims приймає токен і повертає об'єкт Claims —
    // всі дані, які містяться в токені.
    public Claims extractClaims(String token) {
        // Створюється парсер токенів.
        return Jwts.parser()
                // Встановлюється секретний ключ для перевірки підпису токена.
                .setSigningKey(SECRET_KEY)
                // Розбирається токен, перевіряється підпис,
                // і повертаються дані (claims).
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}