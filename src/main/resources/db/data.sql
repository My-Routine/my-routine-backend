# DROP SCHEMA IF EXISTS myroutine;
CREATE SCHEMA IF NOT EXISTS myroutine default character set utf8mb4;
USE myroutine;

INSERT INTO user(id, nickname, password_hash, email, phone, created_at)
VALUES (1, 'user1', '0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90',
        'user1@gmail.com',
        '01011111111', NOW()),
       (2, 'user2', '6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3',
        'user2@naver.com',
        '01022222222', NOW());

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
       (4, 1, '유저 1이 만든 스케쥴 4', false);

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
INSERT INTO day_schedule_work_info(id, start_at, end_at, alert_type_id, day_schedule_id, work_id)
VALUES (1, '11:30:00', '12:30:00', 1, 1, 1),
       (2, '13:30:00', '15:30:00', 1, 1, 1);

# TODO: 20개
INSERT INTO like_schedule(id, user_id, schedule_id)
VALUES (1, 1, 1);

# TODO: like_user 데이터 삽입 20개

# TODO : board 5개
INSERT INTO board(id, user_id, created_at, title, content)
VALUES (1, 1, NOW(), '정재영바보', '재영아쓴소리좀그만해라');

# TODO: like_board 데이터 삽입 20개