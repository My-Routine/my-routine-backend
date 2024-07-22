package com.mbti_j.myroutine.backend.model.dto.request;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import java.util.List;

public class ScheduleRegisterForm {

    private String title;

    private List<DaySchedule> daySchedules;
}
