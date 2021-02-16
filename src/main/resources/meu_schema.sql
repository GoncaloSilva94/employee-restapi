create database employeedb;
use employeedb;

CREATE TABLE status(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(128)
);

CREATE TABLE positions(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(128)
);

CREATE TABLE employee(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    birthdate DATETIME,
    address VARCHAR(255),
    status_id INTEGER REFERENCES status (ID),
    position_id INTEGER REFERENCES positions (ID),
    created DATETIME,
    updated DATETIME
    );
