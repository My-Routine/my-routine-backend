package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work_category.WorkCategorySmallDto;
import com.mbti_j.myroutine.backend.model.entity.WorkCategorySmall;
import com.mbti_j.myroutine.backend.repository.WorkCategorySmallRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkCategorySmallService {

    private final WorkCategorySmallRepository workCategorySmallRepository;
    private final WorkCategoryLargeService workCategoryLargeService;

    public WorkCategorySmall selectSmallCategoryById(
            Long workCategoryLargeId) {
        return workCategorySmallRepository.findById(workCategoryLargeId).orElseThrow();
    }

    public List<WorkCategorySmall> selectAllSmallCategoriesByLargeCategoryId(
            Long workCategoryLargeId) {
        return workCategorySmallRepository.findAllByWorkCategoryLarge(
                workCategoryLargeService.selectLargeCategoryById(workCategoryLargeId));
    }

    public List<WorkCategorySmallDto> searchAllSmallCategoriesByLargeCategoryId(
            Long workCategoryLargeId) {
        List<WorkCategorySmall> workCategorySmallList = selectAllSmallCategoriesByLargeCategoryId(
                workCategoryLargeId);

        List<WorkCategorySmallDto> workCategorySmallDtoList = new ArrayList<>();
        for (WorkCategorySmall workCategorySmall : workCategorySmallList) {
            WorkCategorySmallDto workCategorySmallDto = WorkCategorySmallDto.builder()
                    .id(workCategorySmall.getId())
                    .title(workCategorySmall.getTitle())
                    .workCategoryLargeId(workCategorySmall.getWorkCategoryLarge().getId())
                    .build();
            workCategorySmallDtoList.add(workCategorySmallDto);
        }
        return workCategorySmallDtoList;
    }
}
