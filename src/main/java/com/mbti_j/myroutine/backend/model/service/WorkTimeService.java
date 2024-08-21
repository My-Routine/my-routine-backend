package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeUpdateDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.Work;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import com.mbti_j.myroutine.backend.repository.DayScheduleRepository;
import com.mbti_j.myroutine.backend.repository.WorkRepository;
import com.mbti_j.myroutine.backend.repository.WorkTimeRepository;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkTimeService {

    private final WorkTimeRepository workTimeRepository;
    private final DayScheduleRepository dayScheduleRepository;

    private final DayScheduleService dayScheduleService;
    private final WorkService workService;

    public WorkTime selectWorkTimeById(Long workTimeId) {
        return workTimeRepository.findById(workTimeId).orElseThrow();
    }

    public void registerWorkTime(Long scheduleId, Integer day,WorkTimeRegisterDto workTimeRegisterDto) {
        DaySchedule daySchedule = dayScheduleService.selectDayScheduleByScheduleIdAndDay(scheduleId, day);

        // DTO에서 변환된 Time 객체를 사용하여 WorkTime을 생성
        WorkTime workTime = WorkTime.builder()
                .daySchedule(daySchedule)
                .work(workService.selectWorkById(workTimeRegisterDto.getWorkId()))
                .startAt(workTimeRegisterDto.getStartAtAsTime())
                .endAt(workTimeRegisterDto.getEndAtAsTime())
                .build();

        workTimeRepository.save(workTime);
    }

    public List<WorkTimeInfoDto> findWorkTimesByScheduleIdAndDay(Long scheduleId, Integer day) {
        DaySchedule daySchedule = dayScheduleService.selectDayScheduleByScheduleIdAndDay(scheduleId, day);
        return workTimeRepository.findWorkTimesByDayScheduleOrderByStartAt(daySchedule)
                .stream()
                .map(WorkTimeInfoDto::new)
                .collect(Collectors.toList());
    }


    public void updateWorkTime(Long workTimeId, WorkTimeUpdateDto workTimeUpdateDto) {
        WorkTime updatedWorkTime = WorkTime.builder()
                .id(workTimeId)  // ID는 그대로 유지
                .daySchedule(selectWorkTimeById(workTimeId).getDaySchedule())  // DaySchedule은 그대로 유지
                .work(workService.selectWorkById(workTimeUpdateDto.getWorkId()))  // 새로 업데이트할 작업
                .startAt(workTimeUpdateDto.getStartAtAsTime())  // DTO에서 변환된 시간
                .endAt(workTimeUpdateDto.getEndAtAsTime())  // DTO에서 변환된 시간
                .build();
        workTimeRepository.save(updatedWorkTime);
    }

    public void deleteWorkTime(Long workTimeId) {
        workTimeRepository.deleteById(workTimeId);
    }
}
