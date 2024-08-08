package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.day_schedule.response.DayScheduleDetailDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.repository.DayScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class DayScheduleService {

    private final DayScheduleRepository dayScheduleRepository;
    private final ScheduleService scheduleService;

    public DaySchedule selectDayScheduleById(Long dayScheduleId) {
        return dayScheduleRepository.findById(dayScheduleId).orElseThrow();
    }

    public DayScheduleDetailDto searchDayScheduleById(Long dayScheduleId) {
        DaySchedule daySchedule = selectDayScheduleById(dayScheduleId);
        return DayScheduleDetailDto.builder()
                .id(daySchedule.getId())
                .day(daySchedule.getDay())
                .build();
    }

    public void registerDaySchedule(Long scheduleId, Integer day) {
        DaySchedule daySchedule = DaySchedule.builder()
                .day(day)
                .schedule(scheduleService.selectScheduleById(scheduleId))
                .build();

        dayScheduleRepository.save(daySchedule);
    }
}
