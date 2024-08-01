package com.mbti_j.myroutine.backend.model.dto.response;

public interface LikeScheduleDto {
    Long getScheduleId();
    String getScheduleTitle();
    String getUserNickname();
    long getLikeCount();
}
