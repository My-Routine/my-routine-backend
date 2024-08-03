package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.ArrayList;
//import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthService authService;

    //    public Page<ScheduleInfoDto> getAllSchedulesInfo(Pageable pageable) {
//        return scheduleRepository.findAll(pageable).map(
//                Schedule::toScheduleInfoDto);
//    }
//
//    public ScheduleInfoDto getScheduleInfo(Long scheduleId) {
//        return scheduleRepository.findScheduleInfoDtoById(scheduleId).orElse(null);
//    }

    public Page<ScheduleInfoDto> searchScheduleListByFilter(
            ScheduleSearchFilter scheduleSearchFilter) {
        User logInUser = authService.getLoginUser();
        return scheduleRepository.selectScheduleListByFilter(scheduleSearchFilter, logInUser);
    }

//    public int deleteScheduleInfo(Long scheduleId) {
//        User loginUser = userRepository.findById(1L).orElseThrow();
//
//        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
//
//        if (schedule.getId() == null) {
//            return 0;
//        }
//
//        if (loginUser.equals(
//                schedule.getUser()) /* loginUser.getId() == schedule.getUser().getId() */) {
//            return 0;
//        }
//
//        scheduleRepository.deleteById(schedule.getId());
//        return 1;
//    }
//
//    public int saveScheduleInfo(ScheduleRegisterForm scheduleRegisterForm) {
//        Schedule schedule = scheduleRegisterForm.toScheduleInfoEntity();
//        if (schedule.getId() != null) {
//            return 0;
//        }
//        scheduleRepository.save(schedule);
//        return 1;
//    }

//    public int updateScheduleInfo(Long scheduleId, ScheduleInfoDto scheduleInfoDto) {
//        Schedule schedule = scheduleRepository.findById(scheduleId)
//                .orElseThrow(() -> new RuntimeException("Schedule not found"));
//        if (schedule.getId() == null) {
//            return 0;
//        }
//        scheduleInfoDto.setId(scheduleId);
//        Schedule scheduleEntity = scheduleInfoDto.toScheduleInfoEntity();
//
//        scheduleRepository.save(scheduleEntity);
//        return 1;
//    }
}
