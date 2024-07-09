package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.entity.LikeBoard;
import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.LikeUser;
import org.springframework.transaction.annotation.Transactional;

public interface LikeService {
    LikeSchedule likeSchedule(Long userId, Long scheduleId);

    @Transactional
    int dislikeSchedule(Long userId, Long scheduleId);

    LikeUser likeUser(Long userId, Long scheduleId);

    @Transactional
    int dislikeUser(Long myId, Long userId);

    LikeBoard likeBoard(Long userId, Long boardId);

    @Transactional
    int dislikeBoard(Long userId, Long boardId);
}
