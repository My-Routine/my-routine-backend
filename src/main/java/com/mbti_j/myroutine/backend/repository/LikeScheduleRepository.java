package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.schedule.LikeScheduleDto;
import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeScheduleRepository extends JpaRepository<LikeSchedule, Long> {

    Optional<LikeSchedule> findByUserAndSchedule(User user, Schedule schedule);

    List<LikeSchedule> findByUser(User user);

    Long countByScheduleId(Long scheduleId);


}
