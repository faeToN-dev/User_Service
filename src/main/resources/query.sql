CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO users (name, email)
VALUES ('Iryna', 'iryna4@example.com'),
       ('Dmytro', 'dmytro5@example.com'),
       ('Olga', 'olga6@example.com'),
       ('Viktor', 'viktor7@example.com'),
       ('Nina', 'nina8@example.com'),
       ('Petro', 'petro9@example.com'),
       ('Olexandr', 'olexandr10@example.com');