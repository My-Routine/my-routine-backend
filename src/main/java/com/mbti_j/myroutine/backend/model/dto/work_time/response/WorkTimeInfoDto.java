package com.mbti_j.myroutine.backend.model.dto.work_time.response;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTimeInfoDto {
    private Long workTimeId;
    private Time startAt;
    private Time endAt;
    private String workTitle;
    private Long workId;
    private Long smallCategoryId;
    private Long largeCategoryId;
    private Long dayScheduleId;


}
