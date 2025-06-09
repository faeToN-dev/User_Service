// Оголошення пакету.
package com.example.userservice.repository;
// Імпортування необхідних класів:
// -- UserEntity — сутність, що представляє користувача в базі даних
import com.example.userservice.entity.UserEntity;
// -- JpaRepository — інтерфейс Spring Data JPA, який надає готові методи
// для роботи з базою даних
import org.springframework.data.jpa.repository.JpaRepository;
// -- @Repository — анотація, що вказує на те,
// що цей інтерфейс є репозиторієм
import org.springframework.stereotype.Repository;
// Оголошення інтерфейсу UserRepository:
// -- @Repository — вказує Spring, що цей інтерфейс є репозиторієм і
// його потрібно зареєструвати як компонент у контексті застосунку.
@Repository
// -- extends JpaRepository<UserEntity, Long>:
// -- UserEntity — сутність, з якою працює репозиторій.
// -- Long — тип первинного ключа (id).
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}