package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.request.UserInfoDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /*
     * 회원가입
     */
    @PostMapping("/users")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserSignUpDto userSignUpDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors() ){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(userService.signUpUser(userSignUpDto));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + "다시 시도해주세요" );
        }
    }

    /*
     *  회원 탈퇴
     */
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> withdrawUser(@PathVariable("user-id") Long userId) {
        userService.withdrawUser(userId);
        return ResponseEntity.ok("탈퇴 완료 되었습니다.");
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

