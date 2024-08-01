package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

//    Optional<ScheduleInfoDto> findScheduleInfoDtoById(Long id);

    @Query("SELECT s.id AS scheduleId, s.title AS scheduleTitle, s.user.nickname AS userNickname, s.createdAt AS createdAt " +
            "FROM Schedule s " +
            "WHERE s.user.id = :userId")
    Page<ScheduleInfoDto> findSchedulesByUserId(Long userId, Pageable pageable);

}
