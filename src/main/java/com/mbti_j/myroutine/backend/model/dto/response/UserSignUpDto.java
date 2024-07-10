package com.mbti_j.myroutine.backend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String img;
}
