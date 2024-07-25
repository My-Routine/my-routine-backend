package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.response.UserMyDto;
import com.mbti_j.myroutine.backend.model.dto.response.UserOtherDto;
import com.mbti_j.myroutine.backend.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserMyDto> findMyDtoByIdAndDeletedAtNull(Long userId);

    Optional<UserOtherDto> findOtherDtoByIdAndDeletedAtNull(Long userId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
