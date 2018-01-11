DROP TABLE loginDetail IF EXISTS;

CREATE TABLE users (
  id         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);

CREATE TABLE userDetail (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30),
  password  VARCHAR(50),
  loginStatus  VARCHAR(50)
);

CREATE TABLE loginDetail (
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  userId INTEGER,
  lastLogin  VARCHAR(50)
);
