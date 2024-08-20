package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.LikeScheduleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeScheduleService {
    private final LikeScheduleRepository likeScheduleRepository;
    private final AuthService authService;

    public Boolean selectLikeStatus(User user, Schedule schedule) {
        return likeScheduleRepository.findByUserAndSchedule(user, schedule).isPresent();
    }
    public Long getLikeCount(Long scheduleId){

        return likeScheduleRepository.countByScheduleId(scheduleId);
    }

    public List<Long> getLikedScheduleIdsByLoginUser() {
        // 로그인한 유저 정보 가져오기
        User loginUser = authService.getLoginUser();

        // 해당 유저가 좋아요한 스케줄들의 ID 목록을 가져옴
        return likeScheduleRepository.findByUser(loginUser)
                .stream()
                .map(likeSchedule -> likeSchedule.getSchedule().getId())
                .toList();
    }
    public List<Long> getAllLikeScheduleIds() {
        return likeScheduleRepository.findAll()
                .stream().map(likeSchedule -> likeSchedule.getSchedule().getId())
                .collect(Collectors.toList());
    }
}
