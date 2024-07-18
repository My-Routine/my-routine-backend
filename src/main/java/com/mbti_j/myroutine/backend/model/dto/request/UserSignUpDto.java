package com.mbti_j.myroutine.backend.model.dto.request;

import com.mbti_j.myroutine.backend.model.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Valid
@Data
@AllArgsConstructor
@Builder
public class UserSignUpDto {

    @NotEmpty(message = "Nickname is required")
    private String nickname;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$"
            , message = "특수문자 포함 8자리 이상으로 입력해주세요")
    private String password;

    @Email()
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "Phone number is required")
    private String phone;

    private String img;

    public User toUserEntity() {
        return User.builder()
                .nickname(this.getNickname())
                .passwordHash(this.getPassword())
                .email(this.getEmail())
                .phone(this.getPhone())
                .img(this.getImg())
                .build();
    }
}

