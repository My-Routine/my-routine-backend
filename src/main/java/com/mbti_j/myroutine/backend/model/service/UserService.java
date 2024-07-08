package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.UserInfoDto;

public interface UserService {

    UserInfoDto getUserInfo(Long id);

}
