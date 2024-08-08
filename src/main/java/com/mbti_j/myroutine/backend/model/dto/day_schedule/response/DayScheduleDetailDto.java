package com.mbti_j.myroutine.backend.model.dto.day_schedule.response;

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
public class DayScheduleDetailDto {

    private Long id;
    private Integer day;
}
