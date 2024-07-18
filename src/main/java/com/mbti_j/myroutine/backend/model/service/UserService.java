package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserMyDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserOtherDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import java.sql.Timestamp;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User signUpUser(UserSignUpDto userSignUpDto) {
        //파일 업로드 및 uuid 설정
        userSignUpDto.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        //dto -> entity
        return userRepository.save(userSignUpDto.toUserEntity());
    }

    @Transactional
    public void withdrawUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        //Dirty Checking
        userOptional.ifPresent(user -> {
            //토큰처리

            user.updateDeletedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);

        });

    }

    public UserMyDto getMyInfo(Long userId) {
        return userRepository.findMyDtoByIdAndDeletedAtNull(userId).orElse(null);
    }

    public UserOtherDto getOtherInfo(Long userId) {
        return userRepository.findOtherDtoByIdAndDeletedAtNull(userId).orElse(null);
    }
}
