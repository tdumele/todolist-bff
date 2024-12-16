-- Clean the database
DELETE FROM TASK;
DELETE FROM TODOLIST;
DELETE FROM USER_ACCOUNT;
DELETE FROM USER_ROLES;

-- Insert users into USER_ACCOUNT table
INSERT INTO USER_ACCOUNT (ID, USERNAME, PASSWORD, ACCOUNT_DUE_TIME, ACCOUNT_NON_LOCKED, CREDENTIALS_DUE_TIME, ENABLED, EMAIL)
VALUES
  ('14e310d3-a741-4751-ab54-f378a72e9871', 'user1', 'password1', '2024-12-31 23:59:59', true, '2024-12-31 23:59:59', true, 'user1@example.com'),
  ('24e310d3-a741-4751-ab54-f378a72e9872', 'admin1', 'password2', '2024-12-31 23:59:59', true, '2024-12-31 23:59:59', true, 'admin1@example.com');

-- Insert roles into USER_ROLES table
INSERT INTO USER_ROLES (ID, USER_ID, ROLE)
VALUES
  ('34e318d3-a741-4751-ab54-f378a72e9873', '14e310d3-a741-4751-ab54-f378a72e9871', 'USER'),
  ('44e310d3-a741-4751-ab54-f378a72e9874', '24e310d3-a741-4751-ab54-f378a72e9872', 'ADMIN');

-- Insert data into the TODOLIST table
INSERT INTO TODOLIST(TODOLIST_ID, TODOLIST_TITLE, TODOLIST_DESCRIPTION)
VALUES
  ('a4e310d3-a741-4751-ab54-f378a72e9870', 'Personal', 'Todo list focused on personal tasks'),
  ('d6ceaf6e-26d8-4567-90c5-5beea23bbb02', 'Work', 'Todolist focused on work tasks'),
  ('5deb81bd-8b0b-40f1-9908-d8fef8ed2264', 'Sport', 'Todolist focused on sport tasks (running, swimming, etc.)');

-- Insert tasks
INSERT INTO TASK(TASK_ID, TASK_TITLE, TASK_DESCRIPTION, TASK_CHECKED, TASK_DUE_DATE, TODOLIST_ID) VALUES ('a4e310d3-a741-4751-ab54-f378a72e9870', 'Learn Spring Boot', 'Learn Spring Boot', 'false', '2021-12-31T23:59:59', 'a4e310d3-a741-4751-ab54-f378a72e9870');
INSERT INTO TASK(TASK_ID, TASK_TITLE, TASK_DESCRIPTION, TASK_CHECKED, TODOLIST_ID)
VALUES
  ('d6ceaf6e-26d8-4567-90c5-5beea23bbb02', 'Learn Spring MVC', 'Learn Spring MVC', 'false', 'a4e310d3-a741-4751-ab54-f378a72e9870'),
  ('5deb81bd-8b0b-40f1-9908-d8fef8ed2264', 'Learn Spring Data JPA', 'Learn Spring Data JPA', 'false', 'd6ceaf6e-26d8-4567-90c5-5beea23bbb02');
