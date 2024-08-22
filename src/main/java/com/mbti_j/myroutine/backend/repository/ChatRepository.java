package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.chat.MessagesResponseDto;
import com.mbti_j.myroutine.backend.model.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<ChatRoom, Long>, ChatCustomRepository {

    @Query("SELECT new com.mbti_j.myroutine.backend.model.dto.chat.MessagesResponseDto(cm.id, u.nickname, u.img, cm.message, cm.sendTime, cm.messageType) "
            +
            "FROM ChatMessage cm " +
            "JOIN cm.sender u " +
            "WHERE cm.chatRoom.id = :chatRoomId " +
            "ORDER BY cm.sendTime ASC")
    Page<MessagesResponseDto> findMessageByChatRoomId(Long chatRoomId, Pageable pageable);

}
