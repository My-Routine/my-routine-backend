package com.mbti_j.myroutine.backend.model.dto.response;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import java.sql.Date;
import java.util.List;

public interface ScheduleInfoDto {

    Long getId();

    String getTitle();

    Date getCreatedAt();

    List<DaySchedule> getDaySchedules();
}
