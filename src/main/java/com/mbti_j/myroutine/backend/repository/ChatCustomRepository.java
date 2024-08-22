package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.chat.ChatRoomsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatCustomRepository {

    Page<ChatRoomsResponseDto> findChatRoomByUserId(Long userId, Pageable pageable);

}
