CREATE TABLE products
(
    id       int PRIMARY KEY AUTO_INCREMENT,
    title    varchar(128) NOT NULL,
    price    DECIMAL      NOT NULL,
    quantity INTEGER      NOT NULL
);
