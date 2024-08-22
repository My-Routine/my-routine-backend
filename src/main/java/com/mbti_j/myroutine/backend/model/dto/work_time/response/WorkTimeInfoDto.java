package com.mbti_j.myroutine.backend.model.dto.work_time.response;

import com.mbti_j.myroutine.backend.model.entity.WorkTime;
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

    public WorkTimeInfoDto(WorkTime workTime) {
        this.workTimeId = workTime.getId();
        this.startAt = workTime.getStartAt();
        this.endAt = workTime.getEndAt();
        this.workTitle = workTime.getWork().getTitle();
        this.workId = workTime.getWork().getId();
        this.smallCategoryId = workTime.getWork().getWorkCategorySmall().getId();
        this.largeCategoryId = workTime.getWork().getWorkCategorySmall().getWorkCategoryLarge().getId();
        this.dayScheduleId = workTime.getDaySchedule().getId();
    }
}
