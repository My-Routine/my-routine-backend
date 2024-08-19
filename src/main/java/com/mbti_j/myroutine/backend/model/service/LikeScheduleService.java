package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.LikeScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeScheduleService {
    private final LikeScheduleRepository likeScheduleRepository;

    public Boolean selectLikeStatus(User user, Schedule schedule) {
        return likeScheduleRepository.findByUserAndSchedule(user, schedule).isPresent();
    }
}
