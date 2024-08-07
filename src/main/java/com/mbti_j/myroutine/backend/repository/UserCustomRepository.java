package com.mbti_j.myroutine.backend.repository;

public interface UserCustomRepository {

    void updateProfileImg(Long userId, String imgPath);

    String findPasswordById(Long userId);

    void updatePasswordById(Long userId, String passwordHash);
}
