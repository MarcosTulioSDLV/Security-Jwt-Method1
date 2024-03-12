-- Table for Product
CREATE TABLE IF NOT EXISTS TB_PRODUCT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL UNIQUE,
    product_section VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    country VARCHAR(255) NOT NULL
);

-- Table for User
CREATE TABLE IF NOT EXISTS TB_USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table for Role
CREATE TABLE IF NOT EXISTS TB_ROLE (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    role_name VARCHAR(255) NOT NULL UNIQUE
);

-- Table for the relationship Many-to-Many between User and Role
CREATE TABLE IF NOT EXISTS TB_USER_ROLE (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES TB_USER(id),
    FOREIGN KEY (role_id) REFERENCES TB_ROLE(id)
);
