DROP SCHEMA IF EXISTS myroutine;
CREATE SCHEMA IF NOT EXISTS myroutine default character set utf8mb4;
# ---------------------------------------------------------
use myroutine;

DROP TABLE IF EXISTS user;
CREATE TABLE `user`
(
    `id`            INT AUTO_INCREMENT     NOT NULL,
    `username`      VARCHAR(64)            NOT NULL,
    `password_hash` VARCHAR(64)            NOT NULL,
    `email`         VARCHAR(40)            NOT NULL,
    `phone`         VARCHAR(11)            NOT NULL,
    `img`           VARCHAR(64)            NULL,
    `created_at`    DATETIME DEFAULT NOW() NOT NULL,
    `deleted_at`    DATETIME               NULL,
    `token`         VARCHAR(256)           NULL,
    PRIMARY KEY (`id`)
);

# 아이디 : myroutine, 비밀번호 : myroutine
INSERT INTO user(username, password_hash, email, phone)
VALUES ('myroutine', 'a5a9eb25ebf926e166bd2c07a9914cec29ff0d5dc1977166d064036fc47364fe', 'myroutine@gmail.com',
        '01011111111');

# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `like_user`;
CREATE TABLE `like_user`
(
    `id`           INT AUTO_INCREMENT NOT NULL,
    `user_id_from` INT                NOT NULL,
    `user_id_to`   INT                NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `like_user`
    ADD CONSTRAINT `fk_like_user_user_id_from` FOREIGN KEY (`user_id_from`)
        REFERENCES `user` (`id`);

ALTER TABLE `like_user`
    ADD CONSTRAINT `fk_like_user_user_id_to` FOREIGN KEY (`user_id_to`)
        REFERENCES `user` (`id`);
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `work_category_large`;
CREATE TABLE `work_category_large`
(
    `id`    INT AUTO_INCREMENT NOT NULL,
    `title` VARCHAR(32)        NOT NULL,
    PRIMARY KEY (`id`)
);
INSERT INTO work_category_large
VALUES (1, '운동');

INSERT INTO work_category_large
VALUES (2, '공부');

INSERT INTO work_category_large
VALUES (3, '잠');

INSERT INTO work_category_large
VALUES (4, '기타');
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `work_category_small`;
CREATE TABLE `work_category_small`
(
    `id`                     INT AUTO_INCREMENT NOT NULL,
    `work_category_large_id` INT                NOT NULL,
    `title`                  VARCHAR(32)        NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `work_category_small`
    ADD CONSTRAINT `fk_work_category_small_work_category_large_id` FOREIGN KEY (`work_category_large_id`)
        REFERENCES `work_category_large` (`id`);

INSERT INTO work_category_small
VALUES (1, 1, '헬스');
INSERT INTO work_category_small
VALUES (2, 1, '필라테스');
INSERT INTO work_category_small
VALUES (3, 1, '요가');

INSERT INTO work_category_small
VALUES (1, 2, '자격증');
INSERT INTO work_category_small
VALUES (2, 2, '전공 공부');
INSERT INTO work_category_small
VALUES (3, 2, '교양 공부');
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `alert_type`;
CREATE TABLE `alert_type`
(
    `id`   INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(10)        NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO alert_type
VALUES (1, '카카오톡');
INSERT INTO alert_type
VALUES (2, '문자');
INSERT INTO alert_type
VALUES (3, '이메일');
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`
(
    `id`         INT AUTO_INCREMENT     NOT NULL,
    'user_id'    INT                    NOT NULL,
    `title`      VARCHAR(20)            NOT NULL,
    `created_at` DATETIME DEFAULT NOW() NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `schedule`
    ADD CONSTRAINT `fk_schedule_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`);

INSERT INTO schedule(id, user_id, title)
VALUES (1, 1, '유저 1이 만든 스케쥴 1');

INSERT INTO schedule(id, user_id, title)
VALUES (2, 1, '유저 1이 만든 스케쥴 2');

INSERT INTO schedule(id, user_id, title)
VALUES (3, 1, '유저 1이 만든 스케쥴 3');

INSERT INTO schedule(id, user_id, title)
VALUES (4, 1, '유저 1이 만든 스케쥴 4');
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `day_schedule`;
CREATE TABLE `day_schedule`
(
    `id`          INT AUTO_INCREMENT NOT NULL,
    `schedule_id` INT                NOT NULL,
    `day`         INT                NOT NULL,
    `type`        VARCHAR(10), # fact, plan
    PRIMARY KEY (`id`)
);
ALTER TABLE `day_schedule`
    ADD CONSTRAINT `fk_day_schedule_schedule_id` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedule` (`id`);

# day, schedule_id 묶어서 unique
# 1,2,3,4,5,6,7 : 월화수목금토일
INSERT INTO day_schedule
VALUES (1, 1, 1, 1, 'fact');

INSERT INTO day_schedule
VALUES (2, 1, 2, 1, 'fact');

INSERT INTO day_schedule
VALUES (3, 1, 3, 1, 'fact');

INSERT INTO day_schedule
VALUES (4, 1, 4, 1, 'fact');

INSERT INTO day_schedule
VALUES (5, 1, 5, 1, 'fact');

INSERT INTO day_schedule
VALUES (5, 1, 6, 1, 'fact');

INSERT INTO day_schedule
VALUES (5, 1, 7, 1, 'fact');
# ---------------------------------------------------------
# 네이밍 생각하기
use myroutine;
DROP TABLE IF EXISTS `day_schedule_work_info`;
CREATE TABLE `day_schedule_work_info`
(
    `id`              INT AUTO_INCREMENT NOT NULL,
    `day_schedule_id` INT                NOT NULL,
    `work_id`         INT                NOT NULL,
    `start_at`        TIME               NOT NULL,
    `end_at`          TIME               NOT NULL,
    `alert_type_id`   INT                NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `day_schedule_work_info`
    ADD CONSTRAINT `fk_work_work_id` FOREIGN KEY (`work_id`)
        REFERENCES `work` (`id`);
ALTER TABLE `day_schedule_work_info`
    ADD CONSTRAINT `fk_work_alert_type_id` FOREIGN KEY (`alert_type_id`)
        REFERENCES `alert_type` (`id`);
ALTER TABLE `day_schedule_work_info`
    ADD CONSTRAINT `fk_work_schedule_id` FOREIGN KEY (`day_schedule_id`)
        REFERENCES `day_schecule` (`id`);
# ---------------------------------------------------------

use myroutine;
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work`
(
    `id`                  INT AUTO_INCREMENT NOT NULL,
    `title`               VARCHAR(32)        NOT NULL,
    `work_category_small` INT                NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `work`
    ADD CONSTRAINT `fk_work_work_category_small` FOREIGN KEY (`work_category_small`)
        REFERENCES `work_category_small` (`id`);

INSERT INTO work
VALUES (1, '헬스장', 1);
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `like_schedule`;
CREATE TABLE `like_schedule`
(
    `id`          INT AUTO_INCREMENT NOT NULL,
    `user_id`     INT                NOT NULL,
    `schedule_id` INT                NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `like_schedule`
    ADD CONSTRAINT `fk_like_schedule_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`);

ALTER TABLE `like_schedule`
    ADD CONSTRAINT `fk_like_schedule_schedule_id` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedule` (`id`);

INSERT INTO like_schedule
VALUES (1, 1, 1);
# ---------------------------------------------------------
use myroutine;
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board`
(
    `id`         INT AUTO_INCREMENT     NOT NULL,
    `user_id`    INT                    NOT NULL,
    `title`      VARCHAR(64)            NOT NULL,
    `content`    TEXT                   NOT NULL,
    `created_at` DATETIME DEFAULT NOW() NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `board`
    ADD CONSTRAINT `fk_board_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`);


# ---------------------------------------------------------
use myroutine;

DROP TABLE IF EXISTS `like_board`;
CREATE TABLE `like_board`
(
    `id`       INT AUTO_INCREMENT NOT NULL,
    `user_id`  INT                NOT NULL,
    `board_id` INT                NOT NULL,
    PRIMARY KEY (`id`)
);
ALTER TABLE `like_board`
    ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
ALTER TABLE `like_board`
    ADD FOREIGN KEY (`board_id`) REFERENCES `board` (`id`);
# ---------------------------------------------------------