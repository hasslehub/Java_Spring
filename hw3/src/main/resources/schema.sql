CREATE TABLE IF NOT EXISTS userTable (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    age INT,
    email varchar(50)
);

INSERT INTO userTable (name, age, email) VALUES
('Artur', 23, 'exam1@yandex.ru');