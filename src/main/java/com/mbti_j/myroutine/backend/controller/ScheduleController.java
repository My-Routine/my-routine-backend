package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleDetailDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.service.ScheduleService;
import com.mbti_j.myroutine.backend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return new ResponseEntity<>(scheduleService.searchScheduleListByFilter(
                scheduleSearchFilter), HttpStatus.OK);
    }
    @GetMapping("/{schedule-id}")
    public ResponseEntity<?> searchScheduleById(@PathVariable("schedule-id") Long scheduleId) {
        ScheduleDetailDto scheduleDetailDto = scheduleService.searchScheduleById(scheduleId);
        return new ResponseEntity<>(scheduleDetailDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Schedule> registerSchedule(
            @RequestBody ScheduleRegisterDto scheduleRegisterDto) {
        Schedule savedSchedule = scheduleService.registerSchedule(scheduleRegisterDto);
        return ResponseEntity.ok(savedSchedule);
    }

    @GetMapping("/liked")
    public Page<ScheduleInfoDto> getLikedSchedules(
            @RequestParam(defaultValue = "0") int page, // 기본값 0 (첫 페이지)
            @RequestParam(defaultValue = "10") int size // 기본값 10 (페이지당 항목 수)
    ) {
        // 좋아요한 스케줄 목록을 조회
        return scheduleService.getLikedSchedules(page, size);
    }

    @GetMapping("/popular")
    public Page<ScheduleInfoDto> getPopularSchedules(
            @RequestParam(defaultValue = "0") int page, // 기본값 0 (첫 페이지)
            @RequestParam(defaultValue = "10") int size // 기본값 10 (페이지당 항목 수)
    ) {

        return scheduleService.getSchedulesOrderByLikes(page, size);
    }

}
