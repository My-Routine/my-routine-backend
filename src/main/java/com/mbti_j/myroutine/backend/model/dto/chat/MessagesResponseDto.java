package com.mbti_j.myroutine.backend.model.dto.chat;

import com.mbti_j.myroutine.backend.model.entity.ChatMessage.MessageType;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagesResponseDto {

    private Long messageId;
    private String sender;
    private String profileImg;
    private String message;
    private Timestamp sendTime;
    private MessageType messageType;
    
}
