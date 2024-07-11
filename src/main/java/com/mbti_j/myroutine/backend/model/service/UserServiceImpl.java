package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.request.UserInfoDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUpUser(UserSignUpDto userSignUpDto) {
        //파일 업로드 및 uuid 설정

        //dto -> entity
        User user = User.builder()
                .username(userSignUpDto.getUsername())
                .passwordHash(passwordEncoder.encode(userSignUpDto.getPassword()))
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .img(userSignUpDto.getImg())
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void withdrawUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        //Dirty Checking
        userOptional.ifPresent(user -> {
            //토큰처리

            user.updateDeleteAt(LocalDate.now());
        });

    }


    @Override
    public UserInfoDto getUserInfo(Long id) {
        return userRepository.findById(id)
                .map(User::toUserInfoDto).orElse(null);
    }
}
