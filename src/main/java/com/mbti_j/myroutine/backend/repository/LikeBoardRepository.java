package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.Board;
import com.mbti_j.myroutine.backend.model.entity.LikeBoard;
import com.mbti_j.myroutine.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeBoardRepository extends JpaRepository<LikeBoard, Long> {
    Optional<LikeBoard> findByUserAndBoard(User user, Board board);
}
