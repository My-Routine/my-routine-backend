package com.mbti_j.myroutine.backend.model.dto.work_time.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class WorkTimeRegisterDto {

    private Long workId;
    private String startAt;
    private String endAt;

    public WorkTimeRegisterDto(Long workId, String startAt, String endAt) {
        log.info(workId + startAt + endAt);
        this.workId = workId;
//        this.startAt = TimeConverter.convertStringToSqlTime(startAt);
//        this.endAt = TimeConverter.convertStringToSqlTime(endAt);
    }
}
