// Оголошення пакету
package com.example.userservice.service;
// Імпортування необхідних класів:
// -- сутність користувача, що відображає запис у базі даних
import com.example.userservice.entity.UserEntity;
// -- репозиторій для роботи з базою даних.
import com.example.userservice.repository.UserRepository;
// -- анотація Spring, яка позначає цей клас як сервісний компонент
import org.springframework.stereotype.Service;
// -- використовується для повернення списку користувачів
import java.util.List;
// -- використовується для обробки можливих null значень
// при пошуку користувача за ID
import java.util.Optional;
// Оголошення класу:
// -- @Service вказує Spring, що цей клас є сервісним компонентом
@Service
public class UserService {
    // Оголошення змінної userRepository, яка використовується
    // для взаємодії з базою даних
    // final означає, що після ініціалізації це поле не можна змінити
    private final UserRepository userRepository;
    // Конструктор класу UserService, який отримує екземпляр UserRepository
    // через механізм впровадження залежностей (Dependency Injection) у Spring.
    // Це дозволяє Spring автоматично передавати реалізацію UserRepository
    // під час створення екземпляра UserService.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Метод для створення нового користувача:
    public UserEntity createUser(UserEntity user) {
        // userRepository.save(user) зберігає користувача у базі даних
        // (якщо id немає, створює нового, якщо є — оновлює запис).
        // Повертає збереженого користувача
        return userRepository.save(user);
    }
    // Метод для отримання всіх користувачів:
    public List<UserEntity> getAllUsers() {
        // userRepository.findAll() отримує всі записи з таблиці users.
        // Повертає список (List<UserEntity>) користувачів.
        return userRepository.findAll();
    }
    // Метод для отримання користувача за ID:
    public Optional<UserEntity> getUserById(Long id) {
        // userRepository.findById(id) знаходить користувача у базі за id.
        // Огортає результат в Optional<UserEntity>, що допомагає
        // уникнути NullPointerException.
        return userRepository.findById(id);
    }
    // Метод для оновлення користувача:
    // -- Спочатку шукає користувача за id (findById(id)).
    // -- Якщо знайдено (map(user -> { ... })), змінює name і email,
    // потім зберігає оновленого користувача (userRepository.save(user)).
    // -- Якщо користувача немає, повертає null.
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }
    // Метод для видалення користувача за ID:
    public void deleteUser(Long id) {
        // userRepository.deleteById(id) видаляє запис із бази за його id.
        userRepository.deleteById(id);
    }
}