# 아이디 : myroutine, 비밀번호 : myroutine
USE myroutine;

INSERT INTO user(username, password_hash, email, phone)
VALUES ('myroutine', 'a5a9eb25ebf926e166bd2c07a9914cec29ff0d5dc1977166d064036fc47364fe', 'myroutine@gmail.com',
        '01011111111');

INSERT INTO user(username, password_hash, email, phone)
VALUES ('hyeongseon', '1111', 'hs@naver.com',
        '01022222222');


INSERT INTO work_category_large(id, title)
VALUES (1, '운동');

INSERT INTO work_category_large(id, title)
VALUES (2, '공부');

INSERT INTO work_category_large(id, title)
VALUES (3, '잠');

INSERT INTO work_category_large(id, title)
VALUES (4, '기타');



INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (1, 1, '헬스');
INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (2, 1, '필라테스');
INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (3, 1, '요가');

INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (4, 2, '자격증');
INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (5, 2, '전공 공부');
INSERT INTO work_category_small(id, work_category_large_id, title)
VALUES (6, 2, '교양 공부');

INSERT INTO alert_type(id, name)
VALUES (1, '카카오톡');
INSERT INTO alert_type(id, name)
VALUES (2, '문자');
INSERT INTO alert_type(id, name)
VALUES (3, '이메일');

INSERT INTO schedule(id, user_id, title)
VALUES (1, 1, '유저 1이 만든 스케쥴 1');

INSERT INTO schedule(id, user_id, title)
VALUES (2, 1, '유저 1이 만든 스케쥴 2');

INSERT INTO schedule(id, user_id, title)
VALUES (3, 1, '유저 1이 만든 스케쥴 3');

INSERT INTO schedule(id, user_id, title)
VALUES (4, 1, '유저 1이 만든 스케쥴 4');

# day, schedule_id 묶어서 unique
# 1,2,3,4,5,6,7 : 월화수목금토일
INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (1, 1, 1, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (2, 1, 2, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (3, 1, 3, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (4, 1, 4, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (5, 1, 5, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (6, 1, 6, 'fact');

INSERT INTO day_schedule(id, schedule_id, day, type)
VALUES (7, 1, 7, 'fact');


INSERT INTO work(id, title, work_category_small_id)
VALUES (1, '헬스장', 1);


INSERT INTO like_schedule(id, user_id, schedule_id)
VALUES (1, 1, 1);

INSERT INTO board(id, user_id, created_at, title, content)
VALUES (1, 1, NOW(), '정재영바보', '재영아쓴소리좀그만해라');

