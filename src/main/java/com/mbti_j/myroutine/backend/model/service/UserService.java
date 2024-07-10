package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.UserInfoDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.entity.User;

public interface UserService {

    UserInfoDto getUserInfo(Long id);

    User signUpUser(UserSignUpDto userSignUpDto);
}
