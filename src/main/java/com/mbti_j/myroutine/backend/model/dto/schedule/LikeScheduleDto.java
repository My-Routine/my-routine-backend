package com.mbti_j.myroutine.backend.model.dto.schedule;

public interface LikeScheduleDto {

    Long getScheduleId();

    String getScheduleTitle();

    String getUserNickname();

    long getLikeCount();
}
