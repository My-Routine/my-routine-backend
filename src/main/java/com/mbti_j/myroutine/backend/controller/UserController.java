package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld() {
        User user = new User();
        user.setUsername("myroutine");
        user.setPhone("01050926683");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
