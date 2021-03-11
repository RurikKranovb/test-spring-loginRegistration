-- Table: users
CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
birth_date DATE
)

ENGINE = InnoDB;

-- Insert data

INSERT INTO users VALUE (1,'Пуська','Пупкин', 'Pysbkapypkin@mail.com', '123','2000-2-2')