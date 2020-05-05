SET NAMES utf8;

DROP DATABASE IF EXISTS provider;
CREATE DATABASE provider CHARACTER SET utf8 COLLATE utf8_bin;

USE provider;
-- Create table
SET SQL_SAFE_UPDATES = 1;


CREATE TABLE roles
(
id INTEGER(10) NOT NULL PRIMARY KEY,
name VARCHAR(20) NOT NULL UNIQUE)
ENGINE = InnoDB;

CREATE TABLE USER_ACCOUNT
(
user_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
USER_NAME VARCHAR(30) NOT NULL UNIQUE,
fullname varchar(50) NOT NULL,
GENDER    VARCHAR(50) NOT NULL,
PASSWORD  VARCHAR(30) NOT NULL,
balance FLOAT,
block_status BOOLEAN  NOT NULL DEFAULT FALSE,
role_id INTEGER NOT NULL,
FOREIGN KEY (role_id) REFERENCES roles(id))
ENGINE = InnoDB;


CREATE TABLE services
(
service_id INT(11) NOT NULL AUTO_INCREMENT,
service_name VARCHAR(45) NOT NULL,
service_description VARCHAR(250) NOT NULL,
PRIMARY KEY(service_id))
ENGINE = InnoDB;


CREATE TABLE TARIF
(
CODE  VARCHAR(20) NOT NULL PRIMARY KEY,
NAME  VARCHAR(128) NOT NULL,
PRICE FLOAT NOT NULL,
description VARCHAR(150) NOT NULL,
service_id INT(11) NOT NULL,
FOREIGN KEY (service_id) REFERENCES services(service_id))
ENGINE = InnoDB;

CREATE TABLE users_tarif
(
id_user INTEGER NOT NULL,
code VARCHAR(25) NOT NULL,
payment_status INT(11) NOT NULL DEFAULT 2,
FOREIGN KEY (id_user) REFERENCES user_account(user_id) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (code) REFERENCES tarif(code) ON DELETE RESTRICT ON UPDATE CASCADE)
ENGINE = InnoDB;


INSERT INTO roles VALUES(1, 'admin');
INSERT INTO roles VALUES(2, 'client');


INSERT INTO user_account VALUES(DEFAULT, 'admin', 'Filonitch Dmytriy', 'Male',  'pass',600,DEFAULT, 1);
INSERT INTO user_account VALUES(DEFAULT, 'lambda', 'Nikolay Smirnov', 'Female',  '111',800 ,TRUE,2);
INSERT INTO user_account VALUES(DEFAULT, 'vovik', 'Vladimir Khanjan', 'Male',  '222', 1200,DEFAULT,2);
INSERT INTO user_account VALUES(DEFAULT, 'shurik', 'Alex Dyadchenko', 'Male',  '777', 980,DEFAULT,2);
INSERT INTO user_account VALUES(DEFAULT, 'troynoy_agent', 'Kirill Troyno', 'Male',  '333', 10000,TRUE,2);
INSERT INTO user_account VALUES(DEFAULT, 'vano', 'Ivan Palhnits', 'Male',  'vano', 800,DEFAULT,2);
INSERT INTO user_account VALUES(DEFAULT, 'dimasik', 'Dmytry Oskner', 'Male',  'pinguin', 1200,DEFAULT,2);
INSERT INTO user_account VALUES(DEFAULT,'marusya', 'Mary Radchenko', 'Female', 'pussya', 9999, DEFAULT,2);

INSERT INTO services VALUES (default,'Интернет', 'Безлимитные тарифные планы «Интернет»');
INSERT INTO services VALUES (default,'Цифровое ТВ (IPTV)', 'Просмотр каналов в цифровом формате IPTV ');
INSERT INTO services VALUES (default,'Телефонная связь', 'Городская телефонная связь');

INSERT INTO TARIF VALUES('P001', 'Cтандарт-безлимит', 100, 'Безлимитный интернет со скоростью до 100 мбит/с!',1);
INSERT INTO TARIF VALUES('P002', 'Топ-500 Телеканалов десятилетия!', 90, 'Лучшие телеканалы Европы за последние десять лет в FULLHD разрешении!',2);
INSERT INTO TARIF VALUES('P003', 'Набери-ка номерок', 80, 'Безлимитное кол-во минут по Украине и 60 минут/день на номера по всему миру!',3);
INSERT INTO TARIF VALUES('P004', 'Топ-100 Телеканалов Украины', 45, 'Самые популярные телеканалы на просторе Украины в FULLHD разрешении!',2);
INSERT INTO TARIF VALUES('P005', 'Гигабитный безлимит', 400, 'Гигабитная скорость интернета без единого лимита в трафике!',1);
INSERT INTO TARIF VALUES('P006', 'Эконом-звонки', 40, '60 минут/день по Украине и 30 минут/день по Европы, остальной мир — 1 грн/мин ',3);

INSERT INTO users_tarif VALUES('2', 'P001', 1);
INSERT INTO users_tarif VALUES('3', 'P002', 2);
INSERT INTO users_tarif VALUES('8', 'P005', 1);
INSERT INTO users_tarif VALUES('8', 'P002', 1);
INSERT INTO users_tarif VALUES('8', 'P003', 1);
INSERT INTO users_tarif VALUES('4', 'P001', 2);
INSERT INTO users_tarif VALUES('4', 'P004', 2);
INSERT INTO users_tarif VALUES('1', 'P005', 2);

