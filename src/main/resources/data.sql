-- Clean the database
DELETE FROM TASK;
DELETE FROM TODOLIST;
DELETE FROM USER_ROLES;
DELETE FROM USER_ACCOUNT;

-- Insert users into USER_ACCOUNT table
INSERT INTO USER_ACCOUNT (USER_ID, USER_USERNAME, USER_PASSWORD, USER_ACCOUNT_DUE_TIME, USER_ACCOUNT_NON_LOCKED, USER_CREDENTIALS_DUE_TIME, USER_ENABLED, USER_EMAIL, USER_CREATED_AT, USER_UPDATED_AT)
VALUES
  ('14e310d3-a741-4751-ab54-f378a72e9871', 'user', '$2a$10$D/e0I82EElpaJDb/s8C3xOfpLUa6DaaVkYZtJ3gdGNGBR3vRXRu5m', '2024-12-31 23:59:59', true, '2024-12-31 23:59:59', true, 'user1@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('24e310d3-a741-4751-ab54-f378a72e9872', 'admin1', '$2a$10$/Wr0fV3zBcA6Z1Yxs5Irse2NYDJ.TOJDaLA6K32V1iJL2wa39pe5e', '2024-12-31 23:59:59', true, '2024-12-31 23:59:59', true, 'admin1@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert roles into USER_ROLES table
INSERT INTO USER_ROLES (USER_ID, USER_ROLE)
VALUES
  ('14e310d3-a741-4751-ab54-f378a72e9871', 'USER'),
  ('24e310d3-a741-4751-ab54-f378a72e9872', 'USER'),
  ('24e310d3-a741-4751-ab54-f378a72e9872', 'ADMIN');

-- Insert data into the TODOLIST table
INSERT INTO TODOLIST(TODOLIST_ID, TODOLIST_TITLE, TODOLIST_DESCRIPTION, USER_ID)
VALUES
  ('a4e310d3-a741-4751-ab54-f378a72e9870', 'Personal', 'Todo list focused on personal tasks', '14e310d3-a741-4751-ab54-f378a72e9871'),
  ('d6ceaf6e-26d8-4567-90c5-5beea23bbb02', 'Work', 'Todolist focused on work tasks', '14e310d3-a741-4751-ab54-f378a72e9871'),
  ('5deb81bd-8b0b-40f1-9908-d8fef8ed2264', 'Sport', 'Todolist focused on sport tasks (running, swimming, etc.)', '24e310d3-a741-4751-ab54-f378a72e9872');

-- Insert tasks
INSERT INTO TASK(TASK_ID, TASK_TITLE, TASK_DESCRIPTION, TASK_CHECKED, TASK_DUE_DATE, TODOLIST_ID) VALUES
('a4e310d3-a741-4751-ab54-f378a72e9870', 'Learn Spring Boot', 'Learn Spring Boot', 'false', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
('d6ceaf6e-26d8-4567-90c5-5beea2dbbb02', 'Learn Spring Security', 'Learn Spring Security', 'true', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
('5deb81bd-8b0b-40f1-9908-d8fe78ed2264', 'Learn Spring Data JPA', 'Learn Spring Data JPA', 'true', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
('a4e310d3-a741-4751-ab54-f378872e7870', 'Learn React', 'Learn React to dev web apps', 'true', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
('a4e310d3-a741-4751-ab54-f378372e7870', 'Learn React Native', 'Learn React Native to dev mobile apps', 'true', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870');
INSERT INTO TASK(TASK_ID, TASK_TITLE, TASK_DESCRIPTION, TASK_CHECKED, TODOLIST_ID)
VALUES
  ('d6ceaf6e-26d8-4567-90c5-5beea23bbb02', 'Learn Spring MVC', 'Learn Spring MVC', 'false', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
  ('5deb81bd-8b0b-40f1-9908-d8fef8ed2264', 'Learn Spring Data JPA', 'Learn Spring Data JPA', 'true', 'd6ceaf6e-26d8-4567-90c5-5beea23bbb02');
