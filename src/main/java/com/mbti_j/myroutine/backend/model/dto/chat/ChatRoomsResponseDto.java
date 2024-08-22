package com.mbti_j.myroutine.backend.model.dto.chat;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomsResponseDto {

    private Long chatRoomId;          // 채팅방 ID
    private String chatRoomName;      // 채팅방 이름
    private Long lastMessageId;       // 마지막 메시지 ID
    private String message;           // 마지막 메시지 내용
    private String messageType;       // 마지막 메시지 유형
    private Timestamp sendTime;   // 마지막 메시지 전송 시간
    private String senderNickname;    // 마지막 메시지를 보낸 사람의 닉네임
//    private List<String> participants;


}
