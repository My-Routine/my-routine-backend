package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
