package com.mbti_j.myroutine.backend.model.dto.request;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ScheduleInfoDto {

    private Long id;
    private Long userId;
    private String title;
    private Date createdAt;
    private List<DaySchedule> daySchedules;

    public Schedule toScheduleInfoEntity() {

        return Schedule.builder()
//                .user(userInfoDto)
                .title(this.title)
                .createdAt(this.createdAt)
                .build();
    }
}
