package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.entity.QUser;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateProfileImg(Long userId, String imgPath) {
        log.info("@@@@@@@@@@@@ updateProfileImg 시작");
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
}
