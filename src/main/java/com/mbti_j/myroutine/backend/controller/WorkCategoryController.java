package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.service.WorkCategoryLargeService;
import com.mbti_j.myroutine.backend.model.service.WorkCategorySmallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkCategoryController {

    private final WorkCategorySmallService workCategorySmallService;
    private final WorkCategoryLargeService workCategoryLargeService;

    @GetMapping("/work-categories-large")
    public ResponseEntity<?> getAllWorkCategoryLarge() {
        return new ResponseEntity<>(workCategoryLargeService.searchAllLargeCategories(),
                HttpStatus.OK);
    }

    @GetMapping("/work-categories-large/{work-category-large-id}/work-categories-small")
    public ResponseEntity<?> getWorkCategorySmallByLargeCategoryId(
            @PathVariable("work-category-large-id") Long workCategoryLargeId) {
        return new ResponseEntity<>(
                workCategorySmallService.searchAllSmallCategoriesByLargeCategoryId(
                        workCategoryLargeId), HttpStatus.OK);
    }
}
