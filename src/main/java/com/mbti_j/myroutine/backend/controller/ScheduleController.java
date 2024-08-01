package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.service.ScheduleService;
import com.mbti_j.myroutine.backend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    // TODO : /schedules, Get (모든 스케줄 정보 페이징 처리 후 가져오기)
//    @GetMapping("")
//    public ResponseEntity<?> getAllSchedulesInfo(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<ScheduleInfoDto> schedules = scheduleService.getAllSchedulesInfo(pageable);
//
//        if (schedules.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(schedules, HttpStatus.OK);
//    }
//
//    @GetMapping("/{schedule-id}")
//    public ResponseEntity<?> getScheduleInfo(@PathVariable("schedule-id") Long scheduleId) {
//        ScheduleInfoDto scheduleInfoDto = scheduleService.getScheduleInfo(scheduleId);
//        if (scheduleInfoDto == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(scheduleInfoDto, HttpStatus.OK);
//    }

    @GetMapping("/user-schedules")
    public ResponseEntity<Page<ScheduleInfoDto>> getUserSchedules(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Long userId = 1L; // 임의의 유저 ID 1로 지정
        Page<ScheduleInfoDto> result = scheduleService.getUserSchedules(userId, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
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
