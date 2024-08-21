package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleCustomRepository {

    Schedule saveSchedule(Schedule schedule);

    Page<ScheduleInfoDto> selectScheduleListByFilter(ScheduleSearchFilter scheduleSearchFilter,
            User loginUser);

    Page<ScheduleInfoDto> findLikeSchedulesByIds(List<Long> scheduleIds, int page, int size);

    Page<ScheduleInfoDto> getSchedulesOrderedByLikes(List<Long> scheduleIds, int page, int size, Long userId);

    boolean deleteSchedule(Long userId, Long scheduleId);

    boolean updateSchedule(Long userId, Long scheduleId, ScheduleRegisterDto ScheduleRegisterDto);

}
