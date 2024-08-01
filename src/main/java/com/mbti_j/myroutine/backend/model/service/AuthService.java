package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.user.LoginRequestDto;
import com.mbti_j.myroutine.backend.model.dto.user.LoginUserInfoDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import com.mbti_j.myroutine.backend.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getLogInUser() {
        // 인증
        // Test
        return userRepository.findById(1L).orElse(null);
    }

    @Transactional
    public String login(LoginRequestDto loginRequestDto) {
        //사용자 인증
        log.info("AuthService 로그인 메서드 실행!@@@@@@@@@@@");
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            log.info("이메일 존재 x!@@@@@@@@@@@");
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다.");
        }
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            log.info("비밀번호 일치 x!@@@@@@@@@@@");
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        //Entity -> dto
        LoginUserInfoDto loginUserInfoDto = LoginUserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
        log.info("호출직전@#@#################");
        return jwtUtil.createAccessToken(loginUserInfoDto);
    }

    public boolean logout(String token) {
        //리프레시토큰 사용하게 되면 리프레시 토큰 삭제 로직 추가
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
