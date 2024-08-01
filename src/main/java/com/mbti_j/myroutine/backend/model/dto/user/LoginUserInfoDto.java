package com.mbti_j.myroutine.backend.model.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserInfoDto {

    private Long id;
    private String nickname;
    private String email;
    private String password;
}
