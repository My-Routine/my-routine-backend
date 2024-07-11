package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.ScheduleInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import java.util.List;

public interface ScheduleService {

//    List<ScheduleInfoDto> getAllSchedulesInfo();

    Page<ScheduleInfoDto> getAllSchedulesInfo(Pageable pageable);

    ScheduleInfoDto getScheduleInfo(Long scheduleId);

    int deleteScheduleInfo(Long scheduleId);

    int saveScheduleInfo(ScheduleInfoDto scheduleInfoDto);

    int updateScheduleInfo(Long scheduleId, ScheduleInfoDto scheduleInfoDto);


}
