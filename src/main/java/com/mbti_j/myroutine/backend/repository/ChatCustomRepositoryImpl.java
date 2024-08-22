package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.dto.chat.ChatRoomsResponseDto;
import com.mbti_j.myroutine.backend.model.entity.QChatMessage;
import com.mbti_j.myroutine.backend.model.entity.QChatRoom;
import com.mbti_j.myroutine.backend.model.entity.QParticipants;
import com.mbti_j.myroutine.backend.model.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ChatCustomRepositoryImpl implements ChatCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ChatRoomsResponseDto> findChatRoomByUserId(Long userId, Pageable pageable) {
        QChatRoom qChatRoom = QChatRoom.chatRoom;
        QChatMessage qChatMessage = QChatMessage.chatMessage;
        QParticipants qParticipants = QParticipants.participants;
        QUser qUser = QUser.user;

        // 서브쿼리: 각 채팅방의 마지막 메시지 가져오기
        QChatMessage subChatMessage = new QChatMessage("subChatMessage");

        Long total = queryFactory
                .select(qChatRoom.countDistinct())
                .from(qParticipants)
                .join(qParticipants.chatRoom, qChatRoom)
                .where(qParticipants.user.id.eq(userId))
                .fetchOne();

        if (total == null) {
            total = 0L;
        }

        // 메인 쿼리
        List<ChatRoomsResponseDto> result = queryFactory.select(
                        Projections.constructor(ChatRoomsResponseDto.class,
                                qChatRoom.id.as("chatRoomId"),
                                qChatRoom.roomName.as("chatRoomName"),
                                qChatMessage.id.as("lastMessageId"),
                                qChatMessage.message,
                                qChatMessage.messageType.stringValue(),
                                qChatMessage.sendTime,
                                qUser.nickname.as("senderNickname")
                        ))
                .from(qParticipants)
                .join(qParticipants.chatRoom, qChatRoom)
                .join(qChatRoom.messages, qChatMessage)
                .join(qChatMessage.sender, qUser)
                .where(qParticipants.user.id.eq(userId))
                .where(qChatMessage.sendTime.eq(
                        JPAExpressions.select(subChatMessage.sendTime.max())
                                .from(subChatMessage)
                                .where(subChatMessage.chatRoom.id.eq(qChatRoom.id))
                ))
                .groupBy(qChatRoom.id, qChatMessage.id, qUser.nickname)
                .orderBy(qChatRoom.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(result, pageable, total);
    }


}
