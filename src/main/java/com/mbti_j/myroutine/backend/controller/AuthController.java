package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> logout() {
        //로그아웃 실패시

        // 로그아웃 완료시
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        //로그인 실패시

        // 로그인 완료시
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
