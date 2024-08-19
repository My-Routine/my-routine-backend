package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {
    List<DaySchedule> findByScheduleIdAndDay(Long scheduleId, Integer day);
}

