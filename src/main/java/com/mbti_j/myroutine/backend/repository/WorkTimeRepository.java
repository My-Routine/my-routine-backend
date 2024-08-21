package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WorkTimeRepository extends JpaRepository<WorkTime, Long> {

    List<WorkTime> findWorkTimesByDayScheduleOrderByStartAt(DaySchedule daySchedule);
}
