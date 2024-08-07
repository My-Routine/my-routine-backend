package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.user.ModifyPasswordRequestDto;
import com.mbti_j.myroutine.backend.model.dto.user.UserSignUpDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.model.service.AuthService;
import com.mbti_j.myroutine.backend.model.service.UserService;
import jakarta.persistence.PersistenceException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> signUpUser(
            @RequestPart("img") String img,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("nickname") String nickname,
            @RequestPart("phone") String phone,
            @RequestPart(value = "profileImg", required = false) MultipartFile profileImg) {

        log.info("UserController ==========>");
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .build();
        if (profileImg != null) {
            userService.signUpUser(userSignUpDto, profileImg);
        } else {
            userService.signUpUser(userSignUpDto);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /*
     *  회원 탈퇴
     */
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> withdrawUser(@PathVariable("user-id") Long userId) {
        User loginuser = authService.getLoginUser();
        if (loginuser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.withdrawUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     *  중복체크
     */
    @PostMapping("/user/email-check")
    public ResponseEntity<String> emailCheck(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean exists = userService.checkEmailExists(email);
        return new ResponseEntity<String>(exists ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/nickname-check")
    public ResponseEntity<String> nicknameCheck(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean exists = userService.checkNicknameExists(nickname);
        return new ResponseEntity<String>(exists ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    /* 이메일 인증
     *
     */
    @PostMapping("/user/email-verify")
    public ResponseEntity<String> emailVerify(@RequestBody Map<String, String> request) {

        return null;
    }

    /*
     * 유저 정보 가져오기
     */
    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("user-id") Long userId) {
        User loginuser = authService.getLoginUser();
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
     *  회원 정보 수정 (프로필이미지)
     */
    @PostMapping("/users/modifyProfileImg")
    public ResponseEntity<?> signUpUser(@RequestPart("profileImg") MultipartFile ProfileImg) {
        log.info("UserController modifyProfileImg() ==========>");
        try {
            userService.updateProfileImg(ProfileImg);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PersistenceException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * 비밀번호 수정
     */
    @PostMapping("/users/modifyPassword")
    public ResponseEntity<?> modifyPassword(
            @RequestBody ModifyPasswordRequestDto modifyPasswordRequestDto) {
        log.info("modifyPassword 메서드 실행");
        log.info("modifyPasswordRequestDto: " + modifyPasswordRequestDto.toString());
        if (userService.checkPassword(modifyPasswordRequestDto.getCurrentPassword())) {
            userService.updatePassword(modifyPasswordRequestDto.getNewPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            String msg = "입력하신 비밀번호가 일치하지 않습니다";
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
    }


}

