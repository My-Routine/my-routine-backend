package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.DaySchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {
    Optional<DaySchedule> findByScheduleAndDay(Schedule schedule, Integer day);
}

