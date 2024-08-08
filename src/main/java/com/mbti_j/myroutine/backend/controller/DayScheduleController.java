package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.service.DayScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
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

    @PostMapping("/schedules/{schedule-id}/day-schedules")
    public ResponseEntity<?> registerDaySchedules(
            @PathVariable(name = "schedule-id") Long scheduleId, Integer day) {
        dayScheduleService.registerDaySchedule(scheduleId, day);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
