package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/schedules/{schedule-id}")
    public ResponseEntity<?> likeSchedule(@PathVariable("schedule-id") Long scheduleId) {
        Long userId = 1L;
        if (likeService.likeSchedule(userId, scheduleId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/schedules/{schedule-id}")
    public ResponseEntity<?> dislikeSchedule(@PathVariable("schedule-id") Long scheduleId) {
        Long userId = 1L;
        if (likeService.dislikeSchedule(userId, scheduleId) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO : /likes/users/{user-id}, Post
    @PostMapping("/users/{user-id}")
    public ResponseEntity<?> likeUser(@PathVariable("user-id") Long userId) {
        Long myId = 1L;
        if (likeService.likeUser(myId, userId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO : /likes/users/{user-id}, Delete
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> dislikeUser(@PathVariable("user-id") Long userId) {
        Long myId = 1L;
        if (likeService.dislikeUser(myId, userId) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO : /likes/boards/{board-id}, Post
    @PostMapping("/boards/{board-id}")
    public ResponseEntity<?> likeBoard(@PathVariable("board-id") Long boardId) {
        Long userId = 2L;
        if (likeService.likeBoard(userId, boardId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO : /likes/boards/{board-id}, Delete
    @DeleteMapping("/boards/{board-id}")
    public ResponseEntity<?> dislikeBoard(@PathVariable("board-id") Long boardId) {
        Long userId = 2L;
        if (likeService.dislikeBoard(userId, boardId) == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
