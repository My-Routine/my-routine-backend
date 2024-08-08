package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work.WorkRegisterDto;
import com.mbti_j.myroutine.backend.model.entity.Work;
import com.mbti_j.myroutine.backend.model.entity.WorkCategorySmall;
import com.mbti_j.myroutine.backend.repository.WorkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkCategorySmallService workCategorySmallService;

    public Work saveWork(WorkRegisterDto workRegisterDto) {
        WorkCategorySmall workCategorySmall = workCategorySmallService.selectSmallCategoryById(
                workRegisterDto.getWorkCategorySmallId());

        Work work = Work.builder()
                .title(workRegisterDto.getTitle())
                .workCategorySmall(workCategorySmall)
                .build();

        return workRepository.save(work);
    }

    public Work selectWorkById(Long workId) {
        return workRepository.findById(workId).orElseThrow();
    }

    public List<Work> searchWorksBySmallCategoryId(Long smallCategoryId) {
        WorkCategorySmall workCategorySmall = workCategorySmallService.selectSmallCategoryById(
                smallCategoryId);
        return workRepository.findAllByWorkCategorySmall(workCategorySmall);
    }
}
