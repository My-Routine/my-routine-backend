package com.mbti_j.myroutine.backend.controller;

import com.mbti_j.myroutine.backend.model.dto.chat.ChatRoomsResponseDto;
import com.mbti_j.myroutine.backend.model.dto.chat.MessagesResponseDto;
import com.mbti_j.myroutine.backend.model.service.AuthService;
import com.mbti_j.myroutine.backend.model.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRestController {

    private final AuthService authService;
    private final ChatService chatService;

    /*
     *  채팅방 조회
     */
    @GetMapping("/users/{userId}/chatrooms")
    public ResponseEntity<?> getUserChatRooms(
            @PathVariable("userId") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        log.info("ChatController getUserChatRooms() ===========================> ");

        if (!userId.equals(authService.getLoginUser().getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatRoomsResponseDto> chatRooms = chatService.getUserChatRooms(userId, pageable);

        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }

    /*
     * 채팅방 메세지 조회
     */
    @GetMapping("/chatrooms/{chatRoomId}/messages")
    public ResponseEntity<?> getChatRoomMessages(
            @PathVariable("chatRoomId") Long chatRoomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        log.info("ChatController getChatRoomMessages() ===========================> ");
        Pageable pageable = PageRequest.of(page, size);
        Page<MessagesResponseDto> messages = chatService.getChatMessages(chatRoomId, pageable);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


}
