package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.schedule.LikeScheduleDto;
import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeScheduleRepository extends JpaRepository<LikeSchedule, Long> {

    Optional<LikeSchedule> findByUserAndSchedule(User user, Schedule schedule);

    @Query("SELECT ls.schedule.id AS scheduleId, ls.schedule.title AS scheduleTitle, ls.schedule.user.nickname AS userNickname, COUNT(ls.id) AS likeCount "
            +
            "FROM LikeSchedule ls " +
            "GROUP BY ls.schedule.id, ls.schedule.title, ls.schedule.user.nickname " +
            "ORDER BY likeCount DESC")
    Page<LikeScheduleDto> findSchedulesWithMostLikes(Pageable pageable);

    @Query("SELECT ls.schedule.id AS scheduleId, ls.schedule.title AS scheduleTitle, ls.schedule.user.nickname AS userNickname, COUNT(ls.id) AS likeCount "
            +
            "FROM LikeSchedule ls WHERE ls.user.id = :userId " +
            "GROUP BY ls.schedule.id, ls.schedule.title, ls.schedule.user.nickname " +
            "ORDER BY likeCount DESC")

    Page<LikeScheduleDto> findLikedSchedulesByUserId(@Param("userId") Long userId,
            Pageable pageable);


}
