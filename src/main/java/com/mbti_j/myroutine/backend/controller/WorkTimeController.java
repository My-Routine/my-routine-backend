package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.request.WorkTimeUpdateDto;
import com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto;
import com.mbti_j.myroutine.backend.model.service.WorkTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkTimeController {

    private final WorkTimeService workTimeService;

    @GetMapping("/schedules/{schedule-id}/day/{day}/work-times")
    public ResponseEntity<List<WorkTimeInfoDto>> getWorkTimesByScheduleIdAndDay(
            @PathVariable("schedule-id") Long scheduleId,
            @PathVariable("day") Integer day) {
        List<WorkTimeInfoDto> workTimes = workTimeService.findWorkTimesByScheduleIdAndDay(scheduleId, day);
        return new ResponseEntity<>(workTimes, HttpStatus.OK);
    }


    @PostMapping("/schedules/{schedule-id}/day-schedules/day/{day}/work-times")
    public ResponseEntity<?> createWorkTime(
            @PathVariable(name = "schedule-id") Long scheduleId,
            @PathVariable(name = "day") Integer day,
            @RequestBody WorkTimeRegisterDto workTimeRegisterDto) {
        workTimeService.registerWorkTime(scheduleId, day, workTimeRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/schedules/work-times/{workTimeId}")
    public ResponseEntity<?> updateWorkTime(@PathVariable Long workTimeId, @RequestBody WorkTimeUpdateDto workTimeUpdateDto) {
        log.info("workId: " + workTimeUpdateDto.getWorkId().toString());
        log.info("small category: " + workTimeUpdateDto.getSmallCategoryId().toString());
        log.info("large category: " + workTimeUpdateDto.getLargeCategoryId().toString());
        workTimeService.updateWorkTime(workTimeId, workTimeUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/schedules/work-times/{workTimeId}")
    public ResponseEntity<?> deleteWorkTime(@PathVariable Long workTimeId) {
        workTimeService.deleteWorkTime(workTimeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
