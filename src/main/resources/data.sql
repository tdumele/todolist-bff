-- Description: Data script to insert initial data into the TODOLIST table
DELETE FROM TODOLIST;
INSERT INTO TODOLIST(ID, TITLE, DESCRIPTION, STATUS) VALUES ('a4e310d3-a741-4751-ab54-f378a72e9870', 'Learn Spring Boot', 'Learn Spring Boot', 'IN_PROGRESS');
INSERT INTO TODOLIST(ID, TITLE, DESCRIPTION, STATUS) VALUES ('d6ceaf6e-26d8-4567-90c5-5beea23bbb02', 'Learn Spring MVC', 'Learn Spring MVC', 'IN_PROGRESS');
INSERT INTO TODOLIST(ID, TITLE, DESCRIPTION, STATUS) VALUES ('5deb81bd-8b0b-40f1-9908-d8fef8ed2264', 'Learn Spring Data JPA', 'Learn Spring Data JPA', 'IN_PROGRESS');