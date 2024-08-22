package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.user.LoginRequestDto;
import com.mbti_j.myroutine.backend.model.dto.user.LoginUserInfoDtoForJwt;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import com.mbti_j.myroutine.backend.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public User getLoginUser() {
        // 인증
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String email;
        if (authentication.getPrincipal() instanceof UserDetails) {
            email = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            email = authentication.getPrincipal().toString();
        }
//        return userRepository.findById().orElse(null);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public String login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        //Entity -> dto
        LoginUserInfoDtoForJwt loginUserInfoDtoForJwt = LoginUserInfoDtoForJwt.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();

        String accessToken = jwtUtil.createAccessToken(loginUserInfoDtoForJwt);
        String refreshToken = user.getToken();
        if (refreshToken == null || !jwtUtil.validateToken(refreshToken)) {
            refreshToken = jwtUtil.createRefreshToken(loginUserInfoDtoForJwt);
            user.updateRefreshToken(refreshToken);
            userRepository.save(user);
        }
        return accessToken;
    }

    @Transactional
    public void logout(String token) {
        User user = userRepository.findById(jwtUtil.getUserId(token))
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        user.updateRefreshToken(null);
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
