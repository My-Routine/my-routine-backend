package com.mbti_j.myroutine.backend.model.dto.user;

import lombok.Data;

@Data
public class ModifyPasswordRequestDto {

    private String currentPassword;
    private String newPassword;

}
