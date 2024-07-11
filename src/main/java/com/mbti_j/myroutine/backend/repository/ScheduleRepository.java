package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
