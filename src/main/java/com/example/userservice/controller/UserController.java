// Оголошення пакету.
package com.example.userservice.controller;
// Імпортування необхідних класів:
// -- сутність користувача, яка відображає запис у базі даних.
import com.example.userservice.entity.UserEntity;
// -- сервісний клас для обробки логіки користувачів.
import com.example.userservice.service.UserService;
// -- обгортка для HTTP-відповідей, яка дозволяє керувати статусами.
import org.springframework.http.ResponseEntity;
// -- анотації Spring Boot для створення REST API та обробки HTTP-запитів.
import org.springframework.web.bind.annotation.*;
// -- використовується для повернення списку користувачів.
import java.util.List;
// -- використовується для обробки можливих null значень при пошуку користувача за ID.
import java.util.Optional;
// Оголошення REST-контролера:
// -- @RestController вказує, що цей клас є контролером, який повертає JSON-відповіді.
// -- @RequestMapping("/users") задає базовий шлях для всіх запитів.
@RestController
@RequestMapping("/users")
public class UserController {
    // Оголошення змінної userService, яка використовується для обробки запитів.
            // final означає, що змінну userService не можна змінити після ініціалізації.
    private final UserService userService;
    // Конструктор UserController, що отримує UserService через впровадження залежностей (Dependency Injection).
    // Це дозволяє Spring автоматично передавати екземпляр UserService під час створення UserController.
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Метод для створення користувача:
    // -- @PostMapping — метод обробляє POST /users.
    // -- @RequestBody UserEntity user отримує JSON-об'єкт користувача
    // з тіла запиту.
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        // -- userService.createUser(user) передає дані
        // до сервісу для збереження.
        // -- ResponseEntity.ok(...) — повертає створеного користувача
        // з кодом 200 OK.
        return ResponseEntity.ok(userService.createUser(user));
    }
    // Метод для отримання всіх користувачів:
    // -- @GetMapping — метод обробляє GET /users.
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        // -- userService.getAllUsers() — отримує всіх користувачів з бази.
        // -- ResponseEntity.ok(...) — повертає список користувачів
        // у форматі JSON з кодом 200 OK.
        return ResponseEntity.ok(userService.getAllUsers());
    }
    // Метод для отримання користувача за ID:
    // -- @GetMapping("/{id}") — метод обробляє GET /users/{id}.
    // -- @PathVariable Long id — отримує id користувача з URL.
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        // -- userService.getUserById(id) — шукає користувача в базі.
        Optional<UserEntity> user = userService.getUserById(id);
        // -- Якщо користувач знайдений, повертає його з кодом 200 OK.
        // -- Якщо користувача немає, повертає 404 Not Found.
        return user.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }
    // Метод для оновлення користувача:
    // -- @PutMapping("/{id}") — метод обробляє PUT /users/{id}
    // -- @PathVariable Long id — отримує id користувача з URL
    // -- @RequestBody UserEntity user — отримує оновлені дані користувача з тіла запиту
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        // -- userService.updateUser(id, user) передає дані для оновлення.
        UserEntity updatedUser = userService.updateUser(id, user);
        // -- Якщо оновлення успішне, повертає оновленого користувача з 200 OK.
        // -- Якщо користувача немає, повертає 404 Not Found.
        return updatedUser != null ? ResponseEntity.ok(updatedUser) :
                ResponseEntity.notFound().build();
    }
    // Метод для видалення користувача:
    // -- @DeleteMapping("/{id}") — метод обробляє DELETE /users/{id}
    // -- @PathVariable Long id — отримує id користувача з URL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // -- userService.deleteUser(id) видаляє користувача з бази.
        userService.deleteUser(id);
        // -- ResponseEntity.noContent().build() повертає статус
        // 204 No Content (успішне видалення без тіла відповіді).
        return ResponseEntity.noContent().build();
    }
}