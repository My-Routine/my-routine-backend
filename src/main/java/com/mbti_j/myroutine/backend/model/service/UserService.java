package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.UserInfoDto;

public interface UserService {

    UserInfoDto getUserInfo(Long id);

}
