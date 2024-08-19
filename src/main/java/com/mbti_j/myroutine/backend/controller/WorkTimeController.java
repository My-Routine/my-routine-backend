package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto;
import com.mbti_j.myroutine.backend.model.service.WorkTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkTimeController {

    private final WorkTimeService workTimeService;

    @GetMapping("/schedules/{schedule-id}/day-schedules/{day}/work-times")
    public ResponseEntity<List<WorkTimeInfoDto>> getWorkTimesByScheduleIdAndDay(
            @PathVariable("schedule-id") Long scheduleId,
            @PathVariable("day") Integer day) {
        List<WorkTimeInfoDto> workTimes = workTimeService.findWorkTimesByScheduleIdAndDay(scheduleId, day);
        return new ResponseEntity<>(workTimes, HttpStatus.OK);
    }


    @PostMapping("/schedules/day-schedules/{day-schedule-id}/works")
    public ResponseEntity<?> createDayScheduleWorkInfo(
            @PathVariable(name = "day-schedule-id") Long dayScheduleId,
            @RequestBody WorkTimeRegisterDto workTimeRegisterDto) {
        workTimeService.registerWorkTime(dayScheduleId, workTimeRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/schedules/work-times/{workTimeId}")
    public ResponseEntity<?> updateWorkTime(@PathVariable Long workTimeId, @RequestBody WorkTimeRegisterDto dto) {
        workTimeService.updateWorkTime(workTimeId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
