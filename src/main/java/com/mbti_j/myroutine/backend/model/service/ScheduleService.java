package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleDetailDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.dto.user.UserProfileDto;
import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthService authService;
    private final LikeScheduleService likeScheduleService;

    public Schedule selectScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow();
    }

    public ScheduleDetailDto searchScheduleById(Long scheduleId) {
        Schedule schedule = selectScheduleById(scheduleId);
        User loginUser = authService.getLoginUser();

        return ScheduleDetailDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .userProfileDto(UserProfileDto.builder()
                        .nickname(schedule.getUser().getNickname())
                        .img(schedule.getUser().getImg())
                        .build())
                .createdAt(schedule.getCreatedAt())
                .likeStatus(loginUser != null ? likeScheduleService.selectLikeStatus(loginUser, schedule) : null)
                .build();
    }

    public Page<ScheduleInfoDto> searchScheduleListByFilter(
            ScheduleSearchFilter scheduleSearchFilter) {
        User loginUser = authService.getLoginUser();

        Page<ScheduleInfoDto> scheduleInfoDtoPage = scheduleRepository.selectScheduleListByFilter(scheduleSearchFilter, loginUser);
        scheduleInfoDtoPage.map(scheduleInfoDto -> {
            Schedule schedule = scheduleRepository.findById(scheduleInfoDto.getId()).orElse(null);
            scheduleInfoDto.setLikeStatus(loginUser != null ? likeScheduleService.selectLikeStatus(loginUser, schedule) : null);
            return scheduleInfoDto;
        });
        return scheduleInfoDtoPage;
    }

    public Schedule registerSchedule(ScheduleRegisterDto scheduleRegisterDto) {
        User loginUser = authService.getLoginUser();

        Schedule schedule = Schedule.builder()
                .user(loginUser)
                .title(scheduleRegisterDto.getTitle())
                .hit(0L)
                .visibility(scheduleRegisterDto.getVisibility())
                .build();

        return scheduleRepository.save(schedule);
    }

}
