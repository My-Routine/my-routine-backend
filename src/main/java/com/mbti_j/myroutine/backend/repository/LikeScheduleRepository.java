package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeScheduleRepository extends JpaRepository<LikeSchedule, Long> {
    Optional<LikeSchedule> findByUserAndSchedule(User user, Schedule schedule);
}
