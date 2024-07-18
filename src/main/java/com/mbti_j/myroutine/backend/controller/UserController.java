package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.request.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.model.service.AuthService;
import com.mbti_j.myroutine.backend.model.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    /*
     * 회원가입
     */
    @PostMapping("/users")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody UserSignUpDto userSignUpDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        userService.signUpUser(userSignUpDto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /*
     *  회원 탈퇴
     */
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> withdrawUser(@PathVariable("user-id") Long userId) {
        User loginuser = authService.getLogInUser();
        if (loginuser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.withdrawUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * 유저 정보 가져오기
     */
    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("user-id") Long userId) {
        User loginuser = authService.getLogInUser();
        if (loginuser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 내 정보
        if (loginuser.getId().equals(userId)) {
            return new ResponseEntity<>(userService.getMyInfo(userId), HttpStatus.OK);
        }
        // 다른 사람 정보
        return new ResponseEntity<>(userService.getOtherInfo(userId), HttpStatus.OK);
    }

    /*
     *  로그인한 유저 정보 가져오기
     */


}

