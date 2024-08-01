package com.mbti_j.myroutine.backend.model.dto.schedule;

import com.mbti_j.myroutine.backend.model.constant.SearchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ScheduleSearchFilter {

    private Integer page;

    private Integer size;

    private String keyword;

    private SearchType type;

}
