package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.LikeUser;
import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeUserRepository extends JpaRepository<LikeUser, Long> {
    Optional<LikeUser> findByUserToAndUserFrom(User userTo, User userFrom);
}
