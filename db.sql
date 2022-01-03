drop database if exists appvideo;
create database appvideo;
use appvideo;

CREATE TABLE tags (
                      id int(20) NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      PRIMARY KEY(id)
);
INSERT INTO tags (name) VALUES ('adult');
INSERT INTO tags (name) VALUES ('music');
INSERT INTO tags (name) VALUES ('rap');
INSERT INTO tags (name) VALUES ('polish');
INSERT INTO tags (name) VALUES ('english');

CREATE TABLE filters (
                         id int(20) NOT NULL AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         tagToRemoveId int(20),
                         minViews int(20),
                         maxTitleLength int(20),
                         PRIMARY KEY(id),
                         FOREIGN KEY (tagToRemoveId) REFERENCES tags(id)
);
INSERT INTO filters (name, tagToRemoveId, minViews, maxTitleLength) VALUES ('No filter', null, 0, 255);
INSERT INTO filters (name, tagToRemoveId, minViews, maxTitleLength) VALUES ('No adult', 1, 0, 255);
INSERT INTO filters (name, tagToRemoveId, minViews, maxTitleLength) VALUES ('Only popular', null, 5, 255);
INSERT INTO filters (name, tagToRemoveId, minViews, maxTitleLength) VALUES ('Only short titles', null, 0, 16);


CREATE TABLE users (
                       id int(20) NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       birthday date,
                       filterId int(20) default 1,
                       premium bool,
                       PRIMARY KEY(id),
                       FOREIGN KEY (filterId) REFERENCES filters(id)
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


CREATE TABLE videos (
                        id int(20) NOT NULL AUTO_INCREMENT,
                        title VARCHAR(255) NOT NULL,
                        url VARCHAR(255) NOT NULL,
                        views int(20) default 0,
                        PRIMARY KEY(id)
);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - POLSKIE TANGO (prod. Lanek)', 'i84L16VL6c8', 32);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - Michael Essien Birthday Party (prod. Keeko & Zeppy Zep)', 'wJRAQP4XSmY', 21);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - "Nostalgia" (prod. Rumak)', 'pVrbA5y96sk', 24);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - 4 AM in Girona (prod. Rumak & Borucci)', 'Na9x4CPAsOY', 13);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - "Deszcz na betonie" (prod. Rumak)', 'PCQs3vSJ6xA', 26);
INSERT INTO videos (title, url, views)
VALUES ('FV - Off/On', 'T-zvKHHcP1g', 2);
INSERT INTO videos (title, url, views)
VALUES ('FV - Redwinebeer', '-fArXodLWtA', 3);
INSERT INTO videos (title, url, views)
VALUES ('FV - Bonfire (Remix)', 'j-yDb-1khHE', 4);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - Luxembourg (prod. Lanek)', 'AKI-JFEKtM4', 15);
INSERT INTO videos (title, url, views)
VALUES ('Taco Hemingway - Nie Mam Czasu feat. CatchUp (prod. Rumak)', '9Lt3FlvoBLE', 19);


CREATE TABLE tagsToVideos (
                              id int(20) NOT NULL AUTO_INCREMENT,
                              tagId int(20) NOT NULL,
                              videoId int(20) NOT NULL,
                              PRIMARY KEY(id),
                              FOREIGN KEY (videoId) REFERENCES videos(id),
                              FOREIGN KEY (tagId) REFERENCES tags(id)
);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (1, 1);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (1, 6);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (1, 7);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (1, 8);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (2, 1);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (3, 1);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (4, 1);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (2, 2);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (3, 2);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (4, 2);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (2, 3);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (3, 3);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (4, 3);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (2, 4);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (3, 4);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (5, 4);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (2, 5);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (3, 5);
INSERT INTO tagsToVideos (tagId, videoId) VALUES (4, 5);

CREATE TABLE playlists (
                           id int(20) NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255) NOT NULL,
                           userId int(20) NOT NULL,
                           PRIMARY KEY(id),
                           FOREIGN KEY (userId) REFERENCES users(id)
);
INSERT INTO playlists (name, userId) VALUES ('Polish speaking', 1);
INSERT INTO playlists (name, userId) VALUES ('Adult only', 1);
INSERT INTO playlists (name, userId) VALUES ('English speaking', 2);


CREATE TABLE videosToPlaylists (
                                   id int(20) NOT NULL AUTO_INCREMENT,
                                   videoId int(20) NOT NULL,
                                   playlistId int(20) NOT NULL,
                                   PRIMARY KEY(id),
                                   FOREIGN KEY (videoId) REFERENCES videos(id),
                                   FOREIGN KEY (playlistId) REFERENCES playlists(id)
);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (1, 1);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (1, 2);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (2, 1);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (3, 1);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (4, 3);
INSERT INTO videosToPlaylists (videoId, playlistId) VALUES (5, 1);




CREATE TABLE history (
                         id int(20) NOT NULL AUTO_INCREMENT,
                         userId int(20) NOT NULL,
                         videoId int(20) NOT NULL,
                         PRIMARY KEY(id),
                         FOREIGN KEY (userId) REFERENCES users(id),
                         FOREIGN KEY (videoId) REFERENCES videos(id)
);
INSERT INTO history (userId, videoId) VALUES (1, 1);
INSERT INTO history (userId, videoId) VALUES (1, 6);
INSERT INTO history (userId, videoId) VALUES (1, 2);

select * from tags;
