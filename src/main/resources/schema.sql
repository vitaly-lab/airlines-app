
CREATE DATABASE IF NOT EXISTS airlines;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL PRIVILEGES ON airlines.* TO 'user'@'localhost';CREATE DATABASE IF NOT EXISTS airlines;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT ALL PRIVILEGES ON airlines.* TO 'user'@'localhost';

USE airlines;

DROP TABLE IF EXISTS `airplanes`;
DROP TABLE IF EXISTS `crews_crew_members`;
DROP TABLE IF EXISTS `crews`;
DROP TABLE IF EXISTS `crew_members`;

CREATE TABLE `crews` (
                         `id` INT AUTO_INCREMENT PRIMARY KEY,
                         `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `airplanes` (
                             `id` INT AUTO_INCREMENT PRIMARY KEY,
                             `code name` VARCHAR(10) NOT NULL,
                             `model` VARCHAR(30) NOT NULL,
                             `manufacture date` DATE NOT NULL,
                             `capacity` INT NOT NULL,
                             `flight range` INT NOT NULL,
                             `crews_id` INT,
                             CONSTRAINT `FK_airplanes_crews` FOREIGN KEY (`crews_id`) REFERENCES crews(`id`)
);

CREATE TABLE `crew_members` (
                                `id` INT AUTO_INCREMENT PRIMARY KEY,
                                `first name` VARCHAR(75) NOT NULL,
                                `last name` VARCHAR(75) NOT NULL,
                                `position` VARCHAR(25) NOT NULL,
                                `birthday` DATE NOT NULL,
                                `citizenship` VARCHAR(25) NOT NULL
);


CREATE TABLE `crews_crew_members` (
                                      `crews_id` INT NOT NULL,
                                      `crew_members_id` INT NOT NULL,
                                      PRIMARY KEY (`crews_id`,`crew_members_id`),
                                      CONSTRAINT `FK_crew_members_id` FOREIGN KEY (`crew_members_id`) REFERENCES `crew_members` (`id`),
                                      CONSTRAINT `FK_crews_crew_members` FOREIGN KEY (`crews_id`) REFERENCES `crews` (`id`)
);