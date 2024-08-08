# DROP SCHEMA IF EXISTS myroutine;
CREATE SCHEMA IF NOT EXISTS myroutine default character set utf8mb4;
USE myroutine;

INSERT INTO user(id, nickname, password_hash, email, phone, created_at)
VALUES (1, 'user1', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90',
        'user1@gmail.com', '01011111111', NOW()),
       (2, 'user2', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3',
        'user2@naver.com', '01022222222', NOW()),
       (3, 'user3', '3d9446db3bb1b20d2e4b9fa405e9786b5b8c8b92f156cfd92445ad5d1e3d6e53',
        'user3@domain.com', '01033333333', NOW()),
       (4, 'user4', 'e38ad214943daad1d64c102faec29de4afe9da3d5ebd216f4be5d8c3e94159ee',
        'user4@domain.com', '01044444444', NOW()),
       (5, 'user5', '7c4a8d09ca3762af61e59520943dc26494f8941b76e7a82c42a6b5f660ca7e89',
        'user5@domain.com', '01055555555', NOW()),
       (6, 'user6', 'f7c3bc1d808e04732adf679965ccc34ca7ae3441f4b2a284d8e0d05ed06677d8',
        'user6@domain.com', '01066666666', NOW()),
       (7, 'user7', '72b302bf297a228a75730123efef7c41c23465fe4d080a125a78b7bf070c3130',
        'user7@domain.com', '01077777777', NOW()),
       (8, 'user8', 'd1d0b1320d5864ba7f32b2a8e49c283c805b51c34b6ef171b3cccb35a615b292',
        'user8@domain.com', '01088888888', NOW()),
       (9, 'user9', '6b23c0e52fd43b99e8dfc6a13c1b0586651a052be4d3db2f6b16a4f50b9db03a',
        'user9@domain.com', '01099999999', NOW()),
       (10, 'user10', 'e2ef524fbf3d9fe611d5f29f06cf943ae6549cb1cf6e80b45ed50f93b1d73e11',
        'user10@domain.com', '01010101010', NOW()),
       (11, 'user11', '09f7e02f1290be211da707a266f153b3', 'user11@domain.com', '01011111112',
        NOW()),
       (12, 'user12', '1f3870be274f6c49b3e31a0c6728957f', 'user12@domain.com', '01012121212',
        NOW()),
       (13, 'user13', '8e296a067a37563370ded05f5a3bf3ec', 'user13@domain.com', '01013131313',
        NOW()),
       (14, 'user14', 'f1d3ff8443297732862df21dc4e57262', 'user14@domain.com', '01014141414',
        NOW()),
       (15, 'user15', '6f4922f45568161a8cdf4ad2299f6d23', 'user15@domain.com', '01015151515',
        NOW()),
       (16, 'user16', '1c383cd30b7c298ab50293adfecb7b18', 'user16@domain.com', '01016161616',
        NOW()),
       (17, 'user17', '98f13708210194c475687be6106a3b84', 'user17@domain.com', '01017171717',
        NOW()),
       (18, 'user18', '3c59dc048e8850243be8079a5c74d079', 'user18@domain.com', '01018181818',
        NOW()),
       (19, 'user19', 'b6d767d2f8ed5d21a44b0e5886680cb9', 'user19@domain.com', '01019191919',
        NOW()),
       (20, 'user20', '37693cfc748049e45d87b8c7d8b9aacd', 'user20@domain.com', '01020202020',
        NOW()),
       (21, 'user21', '1ff1de774005f8da13f42943881c655f', 'user21@domain.com', '01021212121',
        NOW()),
       (22, 'user22', '8e296a067a37563370ded05f5a3bf3ec', 'user22@domain.com', '01022222222',
        NOW()),
       (23, 'user23', '9bf31c7ff062936a96d3c8bd1f8f2ff3', 'user23@domain.com', '01023232323',
        NOW()),
       (24, 'user24', 'c74d97b01eae257e44aa9d5bade97baf', 'user24@domain.com', '01024242424',
        NOW()),
       (25, 'user25', '70efdf2ec9b086079795c442636b55fb', 'user25@domain.com', '01025252525',
        NOW()),
       (26, 'user26', '6f4922f45568161a8cdf4ad2299f6d23', 'user26@domain.com', '01026262626',
        NOW()),
       (27, 'user27', '1c383cd30b7c298ab50293adfecb7b18', 'user27@domain.com', '01027272727',
        NOW()),
       (28, 'user28', '98f13708210194c475687be6106a3b84', 'user28@domain.com', '01028282828',
        NOW()),
       (29, 'user29', '3c59dc048e8850243be8079a5c74d079', 'user29@domain.com', '01029292929',
        NOW()),
       (30, 'user30', 'b6d767d2f8ed5d21a44b0e5886680cb9', 'user30@domain.com', '01030303030',
        NOW());


# TODO: 추가 필요
INSERT INTO work_category_large(id, title)
VALUES (1, '운동'),
       (2, '공부'),
       (3, '잠'),
       (4, '기타');


# TODO: 추가 필요
INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (1, 1, '헬스'),
       (2, 1, '필라테스'),
       (3, 1, '요가'),

       (4, 2, '자격증'),
       (5, 2, '전공 공부'),
       (6, 2, '교양 공부');

INSERT INTO alert_type(id, name)
VALUES (1, '카카오톡'),
       (2, '문자'),
       (3, '이메일');

INSERT INTO schedule(id, user_id, title, visibility)
VALUES (1, 1, '유저 1이 만든 스케쥴 1', true),
       (2, 1, '유저 1이 만든 스케쥴 2', true),
       (3, 1, '유저 1이 만든 스케쥴 3', false),
       (4, 1, '유저 1이 만든 스케쥴 4', false),
       (5, 2, '유저 2이 만든 스케쥴 1', true),
       (6, 2, '유저 2이 만든 스케쥴 2', true),
       (7, 2, '유저 2이 만든 스케쥴 3', false),
       (8, 2, '유저 2이 만든 스케쥴 4', false),
       (9, 3, '유저 3이 만든 스케쥴 1', true),
       (10, 3, '유저 3이 만든 스케쥴 2', true),
       (11, 3, '유저 3이 만든 스케쥴 3', false),
       (12, 3, '유저 3이 만든 스케쥴 4', false),
       (13, 4, '유저 4이 만든 스케쥴 1', true),
       (14, 4, '유저 4이 만든 스케쥴 2', true),
       (15, 4, '유저 4이 만든 스케쥴 3', false),
       (16, 4, '유저 4이 만든 스케쥴 4', false),
       (17, 5, '유저 5이 만든 스케쥴 1', true),
       (18, 5, '유저 5이 만든 스케쥴 2', true),
       (19, 5, '유저 5이 만든 스케쥴 3', false),
       (20, 5, '유저 5이 만든 스케쥴 4', false),
       (21, 6, '유저 6이 만든 스케쥴 1', true),
       (22, 6, '유저 6이 만든 스케쥴 2', true),
       (23, 6, '유저 6이 만든 스케쥴 3', false),
       (24, 6, '유저 6이 만든 스케쥴 4', false),
       (25, 7, '유저 7이 만든 스케쥴 1', true),
       (26, 7, '유저 7이 만든 스케쥴 2', true),
       (27, 7, '유저 7이 만든 스케쥴 3', false),
       (28, 7, '유저 7이 만든 스케쥴 4', false),
       (29, 8, '유저 8이 만든 스케쥴 1', true),
       (30, 8, '유저 8이 만든 스케쥴 2', true),
       (31, 8, '유저 8이 만든 스케쥴 3', true);


# TODO: day_schedule_work_info 데이터 넣기

# day, schedule_id 묶어서 unique
# 1,2,3,4,5,6,7 : 월화수목금토일
INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (1, 1, 1, 'fact'),
       (2, 1, 2, 'fact'),
       (3, 1, 3, 'fact'),
       (4, 1, 4, 'fact'),
       (5, 1, 5, 'fact'),
       (6, 1, 6, 'fact'),
       (7, 1, 7, 'fact');

# TODO: work 데이터 넣기
INSERT INTO work(id, title, work_category_small_id)
VALUES (1, '헬스장 가기', 1),
       (2, '자격증 공부', 4);


# WorkInfo
INSERT INTO work_time(id, start_at, end_at, alert_type_id, day_schedule_id, work_id)
VALUES (1, '11:30:00', '12:30:00', 1, 1, 1),
       (2, '13:30:00', '15:30:00', 1, 1, 1);

# TODO: 20개
INSERT INTO like_schedule(id, user_id, schedule_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 4),
       (5, 2, 5),
       (6, 2, 6),
       (7, 3, 7),
       (8, 3, 8),
       (9, 3, 9),
       (10, 4, 10),
       (11, 4, 11),
       (12, 4, 12),
       (13, 5, 13),
       (14, 5, 14),
       (15, 5, 15),
       (16, 6, 16),
       (17, 6, 17),
       (18, 6, 18),
       (19, 7, 19),
       (20, 7, 20),
       (21, 7, 21),
       (22, 8, 22),
       (23, 8, 23),
       (24, 8, 24),
       (25, 9, 25),
       (26, 9, 26),
       (27, 9, 27),
       (28, 10, 28),
       (29, 10, 29),
       (30, 10, 30),
       (31, 2, 1),
       (32, 3, 1),
       (33, 4, 1),
       (34, 5, 1),
       (35, 5, 31);



# TODO: like_user 데이터 삽입 20개

# TODO : board 5개
INSERT INTO board(id, user_id, created_at, title, content)
VALUES (1, 1, NOW(), '정재영바보', '재영아쓴소리좀그만해라');

# TODO: like_board 데이터 삽입 20개
