package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.service.ScheduleService;
import com.mbti_j.myroutine.backend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> searchSchedulesByFilter(
            @ModelAttribute ScheduleSearchFilter scheduleSearchFilter) {
        log.info(scheduleSearchFilter.toString());
        return new ResponseEntity<>(scheduleService.searchScheduleListByFilter(
                scheduleSearchFilter), HttpStatus.OK);
    }
//
//    @GetMapping("/{schedule-id}")
//    public ResponseEntity<?> getScheduleInfo(@PathVariable("schedule-id") Long scheduleId) {
//        ScheduleInfoDto scheduleInfoDto = scheduleService.getScheduleInfo(scheduleId);
//        if (scheduleInfoDto == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(scheduleInfoDto, HttpStatus.OK);
//    }
//
//
//    // TODO : /schedules/{schedule-id}, Delete (스케쥴 삭제)
//    @DeleteMapping("/{schedule-id}")
//    public ResponseEntity<?> deleteScheduleInfo(@PathVariable("schedule-id") Long scheduleId) {
//        if (scheduleService.deleteScheduleInfo(scheduleId) == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> createSchedule(
//            @RequestBody ScheduleRegisterForm scheduleRegisterForm) {
//        if (scheduleService.saveScheduleInfo(scheduleRegisterForm) == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(scheduleRegisterForm, HttpStatus.CREATED);
//    }
//
//    // TODO : /schedules/{schedule-id}, Put (스케쥴 정보 수정하기)
//    @PutMapping("/{schedule-id}")
//    public ResponseEntity<?> updateSchedule(@PathVariable("schedule-id") Long scheduleId,
//            @RequestBody ScheduleInfoDto schedule) {
//        if (scheduleService.updateScheduleInfo(scheduleId, schedule) == 0) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(schedule, HttpStatus.OK);
//    }
//
}
