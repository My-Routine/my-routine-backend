package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.schedule.LikeScheduleDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.Board;
import com.mbti_j.myroutine.backend.model.entity.LikeBoard;
import com.mbti_j.myroutine.backend.model.entity.LikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.LikeUser;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.repository.BoardRepository;
import com.mbti_j.myroutine.backend.repository.LikeBoardRepository;
import com.mbti_j.myroutine.backend.repository.LikeScheduleRepository;
import com.mbti_j.myroutine.backend.repository.LikeUserRepository;
import com.mbti_j.myroutine.backend.repository.ScheduleRepository;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeScheduleRepository likeScheduleRepository;
    private final LikeUserRepository LikeUserRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final BoardRepository boardRepository;
    private final LikeUserRepository likeUserRepository;
    private final LikeBoardRepository likeBoardRepository;
    private final AuthService authService;

    public LikeSchedule likeSchedule(Long userId, Long scheduleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        // LikeSchedule 엔터티 생성 및 필드 설정
        LikeSchedule likeSchedule = new LikeSchedule();
        likeSchedule.setUser(user);
        likeSchedule.setSchedule(schedule);

        // TODO : 데이터가 이미 존재할 때 처리

        // 엔터티 저장
        return likeScheduleRepository.save(likeSchedule);
    }

    public int dislikeSchedule(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
//

        Optional<LikeSchedule> likeSchedule = likeScheduleRepository.findByUserAndSchedule(user,
                schedule);
        if (likeSchedule.isEmpty()) {
            return 0;
        }
        likeScheduleRepository.delete(likeSchedule.get());
        return 1;
    }

    public LikeUser likeUser(Long userTo, Long userFrom) {
        User userT = userRepository.findById(userTo)
                .orElseThrow(() -> new RuntimeException("myId not found"));
        User userF = userRepository.findById(userFrom)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LikeUser likeUser = new LikeUser();
        likeUser.setUserTo(userT);
        likeUser.setUserFrom(userF);

        return LikeUserRepository.save(likeUser);
    }

    public int dislikeUser(Long userTo, Long userFrom) {
        User userT = userRepository.findById(userTo)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User userF = userRepository.findById(userFrom)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<LikeUser> likeUser = LikeUserRepository.findByUserToAndUserFrom(userT, userF);
        if (likeUser.isEmpty()) {
            return 0;
        }
        likeUserRepository.delete(likeUser.get());
        return 1;
    }

    public LikeBoard likeBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        LikeBoard likeBoard = new LikeBoard();
        likeBoard.setUser(user);
        likeBoard.setBoard(board);

        return likeBoardRepository.save(likeBoard);
    }

    public int dislikeBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        Optional<LikeBoard> likeBoard = likeBoardRepository.findByUserAndBoard(user, board);
        if (likeBoard.isEmpty()) {
            return 0;
        }
        likeBoardRepository.delete(likeBoard.get());
        return 1;
    }

}
