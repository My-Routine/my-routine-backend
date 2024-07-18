package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.ScheduleRepository;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Override
//    public List<ScheduleInfoDto> getAllSchedulesInfo() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        List<ScheduleInfoDto> schedulesDto = new ArrayList<>();
//        for (Schedule schedule : schedules) {
//            ScheduleInfoDto scheduleInfoDto = schedule.toScheduleInfoDto();
//            schedulesDto.add(scheduleInfoDto);
//        }
//        return schedulesDto;
//    }

    public Page<ScheduleInfoDto> getAllSchedulesInfo(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(Schedule::toScheduleInfoDto);
    }

    public ScheduleInfoDto getScheduleInfo(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(Schedule::toScheduleInfoDto).orElse(null);
    }

    public int deleteScheduleInfo(Long scheduleId) {
        User loginUser = userRepository.findById(1L).orElseThrow();

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();

        if (schedule.getId() == null) {
            return 0;
        }

        if (loginUser.equals(
                schedule.getUser()) /* loginUser.getId() == schedule.getUser().getId() */) {
            return 0;
        }

        scheduleRepository.deleteById(schedule.getId());
        return 1;
    }

    public int saveScheduleInfo(ScheduleInfoDto scheduleInfoDto) {
        Schedule schedule = scheduleInfoDto.toScheduleInfoEntity();
        if (schedule.getId() != null) {
            return 0;
        }
        scheduleRepository.save(schedule);
        return 1;
    }

    public int updateScheduleInfo(Long scheduleId, ScheduleInfoDto scheduleInfoDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        if (schedule.getId() == null) {
            return 0;
        }
        scheduleInfoDto.setId(scheduleId);
        Schedule scheduleEntity = scheduleInfoDto.toScheduleInfoEntity();

        scheduleRepository.save(scheduleEntity);
        return 1;
    }


}
