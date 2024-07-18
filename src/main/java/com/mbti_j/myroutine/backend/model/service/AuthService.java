package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.LoginFormDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User getLogInUser() {
        // 인증
        // Test
        return userRepository.findById(1L).orElse(null);
    }

    public boolean login(LoginFormDto loginFormDto) {
        // if -> false
        // 인증
        return true;
    }

    public boolean logout(LoginFormDto loginFormDto) {
        // if -> false
        // 인증
        return true;
    }

//    public Optional authenticateUser(UserLoginDto userLoginDto) {
//        String regPhone = "^(01[016789])-?\\d{3,4}-?\\d{4}$";
//        String regEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        Optional<User> userInfoDto = null;
//        String passwordHash = passwordEncoder.encode(userLoginDto.getPassword());
//
//        if (userLoginDto.getIdentifier().matches(regPhone)) {
//            userInfoDto = userRepository.findByPhoneAndPw(
//                    User.builder()
//                            .phone(userLoginDto.getIdentifier())
//                            .passwordHash(passwordHash)
//                            .build()
//            );
//        } else if (userLoginDto.getIdentifier().matches(regEmail)) {
//            userInfoDto = userRepository.findByEmailAndPw(
//                    User.builder()
//                            .email(userLoginDto.getIdentifier())
//                            .passwordHash(passwordHash)
//                            .build()
//            );
//        }
//        if (userInfoDto.isPresent()) {
//
//        }
//        return Optional.ofNullable(userInfoDto);
//    }

}
