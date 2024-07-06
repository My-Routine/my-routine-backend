package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
