package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.repository.ScheduleRepository;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.ArrayList;
//import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //    public Page<ScheduleInfoDto> getAllSchedulesInfo(Pageable pageable) {
//        return scheduleRepository.findAll(pageable).map(
//                Schedule::toScheduleInfoDto);
//    }
//
    public ScheduleInfoDto getScheduleInfo(Long scheduleId) {
        return scheduleRepository.findScheduleInfoDtoById(scheduleId).orElse(null);
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
