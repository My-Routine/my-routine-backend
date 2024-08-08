package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import com.mbti_j.myroutine.backend.repository.WorkTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkTimeService {

    private final WorkTimeRepository workTimeRepository;

    private final DayScheduleService dayScheduleService;
    private final WorkService workService;

    public void registerWorkTime(Long dayScheduleId, WorkTimeRegisterDto workTimeRegisterDto) {
        DaySchedule daySchedule = dayScheduleService.selectDayScheduleById(dayScheduleId);

        WorkTime workTime = WorkTime.builder()
                .daySchedule(daySchedule)
                .work(workService.selectWorkById(workTimeRegisterDto.getWorkId()))
//                .startAt(workTimeRegisterDto.getStartAt())
//                .endAt(workTimeRegisterDto.getEndAt())
                .build();

        workTimeRepository.save(workTime);
    }

}
