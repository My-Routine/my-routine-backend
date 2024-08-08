package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkTimeController {

    private final WorkTimeService workTimeService;

    @PostMapping("/schedules/day-schedules/{day-schedule-id}/works")
    public ResponseEntity<?> createDayScheduleWorkInfo(
            @PathVariable(name = "day-schedule-id") Long dayScheduleId,
            @RequestBody WorkTimeRegisterDto workTimeRegisterDto) {
        workTimeService.registerWorkTime(dayScheduleId, workTimeRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
