package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.request.UserInfoDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     * 회원가입
     */
    @PostMapping("/users")
    public ResponseEntity<?> signUpUser(@RequestBody UserSignUpDto userSignUpDto) {
        try {
            return ResponseEntity.ok(userService.signUpUser(userSignUpDto));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + "다시 시도해주세요" );
        }
    }
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

