// Оголошення пакету
package com.example.userservice.entity;
// Імпортування необхідних класів:
// -- jakarta.persistence.* — містить анотації JPA (Java Persistence API)
// для роботи з базою даних.
import jakarta.persistence.*;
// -- lombok.* — анотації Lombok для автоматичної генерації кодових
// конструкцій, таких як гетери, сетери та конструктори.
import lombok.*;
// Позначення класу як сутності бази даних:
// -- @Entity вказує, що цей клас є сутністю (entity) 
// буде зіставлений з таблицею в базі даних.
// Імпорт класу Serializable.
import java.io.Serializable;
// Serializable — це маркерний інтерфейс Java (інтерфейс без методів),
// який дозволяє об'єкту бути перетвореним у потік байтів (серіалізація) та
// відновленим назад у пам'яті (десеріалізація).
@Entity
// -- @Table(name = "users") — визначає назву таблиці в базі (users).
// Якщо не вказати, назва таблиці буде такою ж, як назва класу (UserEntity).
@Table(name = "users")
// Lombok анотації:
// -- генерує гетери для всіх полів.
@Getter
// -- генерує сетери для всіх полів.
@Setter
// -- створює конструктор без аргументів.
@NoArgsConstructor
// -- створює конструктор з усіма аргументами.
@AllArgsConstructor
// Оголошення класу UserEntity, який описує структуру
// таблиці users в базі даних.
public class UserEntity implements Serializable {
    // Опис первинного ключа:
    // -- вказує поле id як первинний ключ (Primary Key).
    @Id
    // -- визначає, що значення id буде автоматично зростати
    // (автоінкремент у базі даних).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Опис колонки name:
    // -- означає, що значення цього поля не може бути NULL.
    @Column(nullable = false)
    private String name;
    // Опис колонки email:
    // @Column(nullable = false) — значення email обов’язкове.
    // @Column(unique = true) — значення email має бути унікальним
    // (не може бути двох користувачів з однаковою поштою).
    @Column(nullable = false, unique = true)
    private String email;
}