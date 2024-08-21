package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.day_schedule.response.DayScheduleDetailDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.repository.DayScheduleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Long registerDaySchedules(Long scheduleId, Integer day) {
        // day와 scheduleId를 기반으로 DaySchedule을 생성
        DaySchedule daySchedule = DaySchedule.builder()
                .day(day)
                .schedule(scheduleService.selectScheduleById(scheduleId))
                .build();

        dayScheduleRepository.save(daySchedule);

        // 생성된 DaySchedule의 ID 반환
        return daySchedule.getId();
    }

    public DaySchedule selectDayScheduleByScheduleIdAndDay(Long scheduleId, Integer day) {
        Schedule schedule = scheduleService.selectScheduleById(scheduleId);
        return dayScheduleRepository.findByScheduleAndDay(schedule, day).orElse(null);
    }

    public void createSevenDaysBySchedule(Schedule schedule) {
        List<DaySchedule> dayScheduleList = new ArrayList<>();

        for (int day = 1; day <= 7; day++) {
            dayScheduleList.add(DaySchedule.builder()
                    .schedule(schedule)
                    .day(day)
                    .type("fact")
                    .build());
        }

        dayScheduleRepository.saveAll(dayScheduleList);
    }
}
