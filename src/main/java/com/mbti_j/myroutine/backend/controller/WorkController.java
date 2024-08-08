package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.work.WorkRegisterDto;
import com.mbti_j.myroutine.backend.model.entity.Work;
import com.mbti_j.myroutine.backend.model.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @GetMapping("/work-small-categories/{small-category-id}/works")
    public ResponseEntity<?> searchWorksBySmallCategoryId(
            @PathVariable(name = "small-category-id") Long smallCategoryId) {
        return new ResponseEntity<>(workService.searchWorksBySmallCategoryId(smallCategoryId),
                HttpStatus.OK);
    }

    @PostMapping("/works")
    public ResponseEntity<Work> registerWork(@RequestBody WorkRegisterDto workRegisterDto) {
        return new ResponseEntity<>(workService.saveWork(workRegisterDto), HttpStatus.OK);
    }
}
