package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.domain.Page;

public interface ScheduleCustomRepository {

//    ScheduleDetailDto searchScheduleDetailById(Long id);

    Schedule saveSchedule(Schedule schedule);

    Page<ScheduleInfoDto> selectScheduleListByFilter(ScheduleSearchFilter scheduleSearchFilter,
            User loginUser);
}
