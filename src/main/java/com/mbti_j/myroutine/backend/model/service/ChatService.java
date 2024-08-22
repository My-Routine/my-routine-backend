package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.dto.chat.ChatRoomsResponseDto;
import com.mbti_j.myroutine.backend.model.dto.chat.MessagesResponseDto;
import com.mbti_j.myroutine.backend.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Page<ChatRoomsResponseDto> getUserChatRooms(Long userId, Pageable pageable) {
        return chatRepository.findChatRoomByUserId(userId, pageable);
    }

    public Page<MessagesResponseDto> getChatMessages(Long chatRoomId, Pageable pageable) {
        return chatRepository.findMessageByChatRoomId(chatRoomId, pageable);
    }
}
