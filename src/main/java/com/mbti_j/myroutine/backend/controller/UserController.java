package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.UserInfoDto;
import com.mbti_j.myroutine.backend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     * 유저 정보 가져오기
     */
    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("user-id") Long userId) {
        // 마이페이지 (수정)
        UserInfoDto user = userService.getUserInfo(userId);
        if (!validateUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public boolean validateUserExist(UserInfoDto user) {
        if (user == null) {
            return false;
        }
        return true;
    }
}

