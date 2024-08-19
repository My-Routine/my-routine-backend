package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.Work;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import com.mbti_j.myroutine.backend.repository.DayScheduleRepository;
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

    public void registerWorkTime(Long dayScheduleId, WorkTimeRegisterDto workTimeRegisterDto) {
        DaySchedule daySchedule = dayScheduleService.selectDayScheduleById(dayScheduleId);

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
        return workTimeRepository.findWorkTimesByScheduleIdAndDay(scheduleId, day);
    }


    public void updateWorkTime(Long workTimeId, WorkTimeRegisterDto dto) {
        WorkTime existingWorkTime = workTimeRepository.findById(workTimeId)
                .orElseThrow();

        Work updatedWork = workService.selectWorkById(dto.getWorkId());

        // 기존 데이터를 새로 빌드하여 업데이트
        WorkTime updatedWorkTime = WorkTime.builder()
                .id(existingWorkTime.getId())  // ID는 그대로 유지
                .daySchedule(existingWorkTime.getDaySchedule())  // DaySchedule은 그대로 유지
                .work(updatedWork)  // 새로 업데이트할 작업
                .startAt(dto.getStartAtAsTime())  // DTO에서 변환된 시간
                .endAt(dto.getEndAtAsTime())  // DTO에서 변환된 시간
                .build();

        workTimeRepository.save(updatedWorkTime);
    }





}
