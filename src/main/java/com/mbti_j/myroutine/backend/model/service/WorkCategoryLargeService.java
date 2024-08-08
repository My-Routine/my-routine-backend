package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.work_category.WorkCategoryLargeDto;
import com.mbti_j.myroutine.backend.model.entity.WorkCategoryLarge;
import com.mbti_j.myroutine.backend.repository.WorkCategoryLargeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkCategoryLargeService {

    private final WorkCategoryLargeRepository workCategoryLargeRepository;

    public WorkCategoryLarge selectLargeCategoryById(long largeCategoryId) {
        return workCategoryLargeRepository.findById(largeCategoryId)
                .orElseThrow();

    }

    public WorkCategoryLargeDto searchLargeCategoryById(long largeCategoryId) {
        WorkCategoryLarge workCategoryLarge = selectLargeCategoryById(largeCategoryId);
        return WorkCategoryLargeDto.builder()
                .id(workCategoryLarge.getId())
                .title(workCategoryLarge.getTitle())
                .build();
    }


    public List<WorkCategoryLargeDto> searchAllLargeCategories() {
        List<WorkCategoryLarge> workCategoryLargeList = workCategoryLargeRepository.findAll();

        List<WorkCategoryLargeDto> workCategoryLargeDtoList = new ArrayList<>();
        for (WorkCategoryLarge workCategoryLarge : workCategoryLargeList) {
            WorkCategoryLargeDto workCategoryLargeDto = WorkCategoryLargeDto.builder()
                    .id(workCategoryLarge.getId())
                    .title(workCategoryLarge.getTitle())
                    .build();
            workCategoryLargeDtoList.add(workCategoryLargeDto);
        }
        return workCategoryLargeDtoList;

    }
}
