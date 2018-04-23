CREATE TABLE IF NOT EXISTS `books` (
 `id` int NOT NULL AUTO_INCREMENT,
 `title`varchar(255),
 `author_id` int,
 PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `authors` (
 `id` int NOT NULL AUTO_INCREMENT,
 `firstName`varchar(255),
 `lastName`varchar(255),
 PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `users` (
 `id` int NOT NULL AUTO_INCREMENT,
 `firstName`varchar(255),
 `lastName`varchar(255),
 PRIMARY KEY (`id`)
);