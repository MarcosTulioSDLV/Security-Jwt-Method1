-- Insert products
INSERT INTO TB_PRODUCT (product_code, name, product_section, price, creation_date, country)
VALUES ('1', 'Pc', 'Tech', 1000, CURRENT_TIMESTAMP, 'Colombia');

INSERT INTO TB_PRODUCT (product_code, name, product_section, price, creation_date, country)
VALUES ('2', 'Tv', 'Tech', 800, CURRENT_TIMESTAMP, 'Colombia');

INSERT INTO TB_PRODUCT (product_code, name, product_section, price, creation_date, country)
VALUES ('3', 'Ball', 'Sports', 100, CURRENT_TIMESTAMP, 'Brazil');

-- Insert roles
INSERT INTO TB_ROLE (role_name) VALUES ('ROLE_USER');
INSERT INTO TB_ROLE (role_name) VALUES ('ROLE_ADMIN');

-- Insert users
INSERT INTO TB_USER (username, password) VALUES ('user1', '$2a$10$UsEz/4Ic.UTpMfsnFB8hjO1hV4GR5aBS1s91f6Ox02dn0N8WNrZcW');
INSERT INTO TB_USER (username, password) VALUES ('user2', '$2a$10$UsEz/4Ic.UTpMfsnFB8hjO1hV4GR5aBS1s91f6Ox02dn0N8WNrZcW');
INSERT INTO TB_USER (username, password) VALUES ('user3', '$2a$10$UsEz/4Ic.UTpMfsnFB8hjO1hV4GR5aBS1s91f6Ox02dn0N8WNrZcW');

-- For Relationship between Users and Roles
INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
  ((SELECT id FROM TB_USER WHERE username = 'user1'), (SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_USER'));

INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
  ((SELECT id FROM TB_USER WHERE username = 'user2'), (SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_ADMIN'));

INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
  ((SELECT id FROM TB_USER WHERE username = 'user3'), (SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_USER'));

INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
  ((SELECT id FROM TB_USER WHERE username = 'user3'), (SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_ADMIN'));
