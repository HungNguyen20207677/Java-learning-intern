-- CREATE DATABASE exercise8;


CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100)
);

INSERT INTO accounts (username, password)
VALUES 
('john_doe', 'password123'),
('jane_smith', 'letmein'),
('mike_jackson', 'qwerty'),
('emily_wilson', 'secret'),
('alex_brown', '123456'),
('sara_taylor', 'football'),
('chris_evans', 'p@ssw0rd'),
('amanda_johnson', 'hello123'),
('kevin_anderson', 'welcome'),
('lisa_davis', 'password');
