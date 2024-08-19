package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto;
import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {
    @Query("SELECT new com.mbti_j.myroutine.backend.model.dto.work_time.response.WorkTimeInfoDto(wt.id, wt.startAt, wt.endAt, w.title, w.id, sc.id, lc.id, ds.id) " +
            "FROM WorkTime wt " +
            "JOIN wt.daySchedule ds " +
            "JOIN wt.work w " +
            "JOIN w.workCategorySmall sc " +
            "JOIN sc.workCategoryLarge lc " +
            "WHERE ds.schedule.id = :scheduleId " +
            "AND ds.day = :day")
    List<WorkTimeInfoDto> findWorkTimesByScheduleIdAndDay(@Param("scheduleId") Long scheduleId, @Param("day") Integer day);


}
