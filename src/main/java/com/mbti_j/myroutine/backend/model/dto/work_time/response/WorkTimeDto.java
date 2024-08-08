package com.mbti_j.myroutine.backend.model.dto.work_time.response;

import java.sql.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkTimeDto {

    private Long id;
    private String workTitle;
    private Time startAt;
    private Time endAt;

    public WorkTimeDto(Long id, String workTitle, Time startAt, Time endAt) {
        this.id = id;
        this.workTitle = workTitle;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}