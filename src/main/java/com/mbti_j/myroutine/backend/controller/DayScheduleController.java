package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.service.DayScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DayScheduleController {

    private final DayScheduleService dayScheduleService;

    @GetMapping("/schedules/day-schedules/{day-schedule-id}")
    public ResponseEntity<?> searchDaySchedules(
            @PathVariable("day-schedule-id") Long dayScheduleId) {
        return new ResponseEntity<>(dayScheduleService.searchDayScheduleById(dayScheduleId),
                HttpStatus.OK);
    }

//    @PostMapping("/schedules/{schedule-id}/day-schedules")
//    public ResponseEntity<?> registerDaySchedules(
//            @PathVariable(name = "schedule-id") Long scheduleId, Integer day) {
//        dayScheduleService.registerDaySchedule(scheduleId, day);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
@PostMapping("/schedules/{schedule-id}/day-schedules")
public ResponseEntity<Long> registerDaySchedules(
        @PathVariable(name = "schedule-id") Long scheduleId,
        @RequestParam(name = "day") Integer day) {

    // DaySchedule 생성 및 ID 반환
    Long dayScheduleId = dayScheduleService.registerDaySchedules(scheduleId, day);

    // 생성된 DaySchedule의 ID를 반환
    return new ResponseEntity<>(dayScheduleId, HttpStatus.OK);
}


}
