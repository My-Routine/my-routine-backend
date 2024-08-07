package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateProfileImg(Long userId, String imgPath) {
        try {
            QUser user = QUser.user;

            JPAUpdateClause update = new JPAUpdateClause(em, user);
            update
                    .where(user.id.eq(userId))
                    .set(user.img, imgPath)
                    .execute();
            //        em.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String findPasswordById(Long userId) {
        return queryFactory.select(QUser.user.passwordHash)
                .from(QUser.user)
                .where(QUser.user.id.eq(userId))
                .fetchOne();
    }

    @Override
    public void updatePasswordById(Long userId, String passwordHash) {
        new JPAUpdateClause(em, QUser.user)
                .where(QUser.user.id.eq(userId))
                .set(QUser.user.passwordHash, passwordHash)
                .execute();
    }

}
