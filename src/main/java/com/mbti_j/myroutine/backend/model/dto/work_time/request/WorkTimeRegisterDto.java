package com.mbti_j.myroutine.backend.model.dto.work_time.request;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
@AllArgsConstructor
@Builder
public class WorkTimeRegisterDto {
    private Long workId;
    private String startAt;
    private String endAt;

    public Time getStartAtAsTime() {
        // "HH:MM" 형식의 문자열을 받아 "HH:MM:00"으로 변환한 후 Time 객체로 변환
        LocalTime localTime = LocalTime.parse(startAt.length() == 5 ? startAt + ":00" : startAt);
        return Time.valueOf(localTime);
    }

    public Time getEndAtAsTime() {
        // "HH:MM" 형식의 문자열을 받아 "HH:MM:00"으로 변환한 후 Time 객체로 변환
        LocalTime localTime = LocalTime.parse(endAt.length() == 5 ? endAt + ":00" : endAt);
        return Time.valueOf(localTime);
    }


}
