drop database if exists appvideo;
create database appvideo;
use appvideo;


CREATE TABLE users (
                       id int(20) NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       birthday date,
                       premium bool,
                       PRIMARY KEY(id)
);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('user', 'user',  'user', 'user', 'user', '1970-01-01', true);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('user2', 'user2',  'use2', 'user2', 'user2', '1970-01-01', true);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('user3', 'user3',  'user3', 'user3', 'user3', '1970-01-01', false);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('user4', 'user4',  'user4', 'use4', 'user4', '1970-01-01', false);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('user5', 'user5',  'user5', 'user5', 'user5', '1970-01-01', false);
INSERT INTO users (name, surname, email, username, password, birthday, premium)
VALUES ('admin', 'admin',  'admin', 'admin', 'admin', '1970-01-01', true);



CREATE TABLE videos (
                        id int(20) NOT NULL AUTO_INCREMENT,
                        title VARCHAR(255) NOT NULL,
                        url VARCHAR(255) NOT NULL,
                        tagList VARCHAR(255) NOT NULL,
                        PRIMARY KEY(id)
);
INSERT INTO videos (title, url, tagList)
VALUES ('Taco Hemingway - POLSKIE TANGO (prod. Lanek)', 'i84L16VL6c8',  'music,rap,polish');
INSERT INTO videos (title, url, tagList)
VALUES ('Taco Hemingway - Michael Essien Birthday Party (prod. Keeko & Zeppy Zep)', 'wJRAQP4XSmY',  'music,rap,polish');
INSERT INTO videos (title, url, tagList)
VALUES ('Taco Hemingway - "Nostalgia" (prod. Rumak)', 'pVrbA5y96sk', 'music,rap,polish');

