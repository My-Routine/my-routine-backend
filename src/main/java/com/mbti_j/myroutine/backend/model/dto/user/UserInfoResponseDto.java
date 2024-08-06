package com.mbti_j.myroutine.backend.model.dto.user;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResponseDto {

    Long id;
    String nickname;
    String email;
    String img;
    String phone;
    Date createAt;


}
