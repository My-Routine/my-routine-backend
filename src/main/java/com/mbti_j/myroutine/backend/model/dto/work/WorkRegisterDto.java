package com.mbti_j.myroutine.backend.model.dto.work;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkRegisterDto {

    private Long id;
    private Long workCategorySmallId;
    private String title;
}
