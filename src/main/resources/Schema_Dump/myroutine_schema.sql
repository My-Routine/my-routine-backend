DROP SCHEMA IF EXISTS myroutine;
CREATE SCHEMA IF NOT EXISTS myroutine default character set utf8mb4;

use myroutine;

DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `username` VARCHAR(64) NOT NULL ,
    `password_hash` VARCHAR(64)  NOT NULL ,
    `email` VARCHAR(40) NOT NULL ,
    `phone` VARCHAR(11) NOT NULL ,
    `img` VARCHAR(64)  NULL ,
    `created_at` DATETIME DEFAULT NOW() NOT NULL ,
    `deleted_at` DATETIME NULL ,
    `token` VARCHAR(256) NULL ,
    PRIMARY KEY (`id`)
);

# myroutine
INSERT INTO user(username, password_hash, email, phone) VALUES('myroutine', 'a5a9eb25ebf926e166bd2c07a9914cec29ff0d5dc1977166d064036fc47364fe', 'myroutine@gmail.com', '01011111111');


DROP TABLE IF EXISTS `user_like_user`;
CREATE TABLE `user_like_user` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `user_id_from` INT NOT NULL ,
    `user_id_to` INT NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `work_category_large`;
CREATE TABLE `work_category_large` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `title` VARCHAR(32)  NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `work_category_small`;
CREATE TABLE `work_category_small` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `work_category_large_id` INT  NOT NULL ,
    `title` VARCHAR(32)  NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `title` VARCHAR(32)  NOT NULL ,
    `work_category_small` INT NULL ,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `alert_type`;
CREATE TABLE `alert_type` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `name` VARCHAR(10)  NOT NULL ,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `title` VARCHAR(20)  NOT NULL ,
    `created_at` DATETIME DEFAULT NOW()  NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `day_schecule`;
CREATE TABLE `day_schecule` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `day` INT  NOT NULL ,
    `schedule_id` INT  NOT NULL ,
    `work_id` INT  NOT NULL ,
    `start_at` TIME  NOT NULL ,
    `end_at` TIME NOT NULL ,
    `alert_type_id` INT NULL ,
    `type` VARCHAR(10), # fact, plan
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_like_schedule`;
CREATE TABLE `user_like_schedule` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `user_id` INT  NOT NULL ,
    `schedule_id` INT  NOT NULL ,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `user_id` INT  NOT NULL ,
    `title` VARCHAR(64)  NOT NULL ,
    `content` TEXT  NOT NULL ,
    `created_at` DATETIME DEFAULT NOW() NOT NULL ,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `user_like_board`;
CREATE TABLE `user_like_board` (
    `id` INT AUTO_INCREMENT NOT NULL ,
    `user_id` INT NOT NULL ,
    `board_id` INT NOT NULL ,
    PRIMARY KEY (`id`)
);
ALTER TABLE `user_like_board` ADD FOREIGN KEY(`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `user_like_board` ADD FOREIGN KEY(`board_id`) REFERENCES `board` (`id`);
# -----------------

ALTER TABLE `user_like_user` ADD CONSTRAINT `fk_user_like_user_user_id_from` FOREIGN KEY(`user_id_from`)
REFERENCES `user` (`id`);

ALTER TABLE `user_like_user` ADD CONSTRAINT `fk_user_like_user_user_id_to` FOREIGN KEY(`user_id_to`)
REFERENCES `user` (`id`);

ALTER TABLE `work_category_small` ADD CONSTRAINT `fk_work_category_small_work_category_large_id` FOREIGN KEY(`work_category_large_id`)
REFERENCES `work_category_large` (`id`);

ALTER TABLE `work` ADD CONSTRAINT `fk_work_work_category_small` FOREIGN KEY(`work_category_small`)
REFERENCES `work_category_small` (`id`);

ALTER TABLE `day_schecule` ADD CONSTRAINT `fk_day_schecule_schedule_id` FOREIGN KEY(`schedule_id`)
REFERENCES `schedule` (`id`);

ALTER TABLE `day_schecule` ADD CONSTRAINT `fk_day_schecule_work_id` FOREIGN KEY(`work_id`)
REFERENCES `work` (`id`);

ALTER TABLE `day_schecule` ADD CONSTRAINT `fk_day_schecule_alert_type_id` FOREIGN KEY(`alert_type_id`)
REFERENCES `alert_type` (`id`);

ALTER TABLE `user_like_schedule` ADD CONSTRAINT `fk_user_like_schedule_user_id` FOREIGN KEY(`user_id`)
REFERENCES `user` (`id`);

ALTER TABLE `user_like_schedule` ADD CONSTRAINT `fk_user_like_schedule_schedule_id` FOREIGN KEY(`schedule_id`)
REFERENCES `schedule` (`id`);

ALTER TABLE `board` ADD CONSTRAINT `fk_board_user_id` FOREIGN KEY(`user_id`)
REFERENCES `user` (`id`);

