package com.mbti_j.myroutine.backend.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchType {

    MY("MY"),
    OTHER("OTHER"),
    NO_LOGIN_USER("로그인한 유저가 없습니더.");

    private final String value;
}
