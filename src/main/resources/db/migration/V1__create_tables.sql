-- Таблица пользователей
CREATE TABLE users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Таблица категорий
CREATE TABLE categories
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

-- Таблица товаров
CREATE TABLE products
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    category_id BIGINT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id)
);

-- Таблица корзины
CREATE TABLE cart_items
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT,
    product_id BIGINT,
    quantity   INT NOT NULL,
    CONSTRAINT fk_user_cart FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_product_cart FOREIGN KEY (product_id) REFERENCES products (id)
);

-- Таблица заказов
CREATE TABLE orders
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT,
    total_price DECIMAL(10, 2) NOT NULL,
    order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_order FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Таблица позиций в заказах
CREATE TABLE order_items
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id   BIGINT,
    product_id BIGINT,
    quantity   INT NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_product_order FOREIGN KEY (product_id) REFERENCES products (id)
);
