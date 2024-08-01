package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.domain.Page;

public interface ScheduleCustomRepository {

    Page<ScheduleInfoDto> selectScheduleListByFilter(ScheduleSearchFilter scheduleSearchFilter,
            User loginUser);
}
