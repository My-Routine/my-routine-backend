package com.mbti_j.myroutine.backend.repository;

import static com.mbti_j.myroutine.backend.model.entity.QDaySchedule.daySchedule;
import static com.mbti_j.myroutine.backend.model.entity.QLikeSchedule.likeSchedule;
import static com.mbti_j.myroutine.backend.model.entity.QWorkTime.workTime;

import com.mbti_j.myroutine.backend.model.constant.SearchType;
import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleRegisterDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.request.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.dto.schedule.response.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.entity.QDaySchedule;
import com.mbti_j.myroutine.backend.model.entity.QLikeSchedule;
import com.mbti_j.myroutine.backend.model.entity.QSchedule;
import com.mbti_j.myroutine.backend.model.entity.QUser;
import com.mbti_j.myroutine.backend.model.entity.QWork;
import com.mbti_j.myroutine.backend.model.entity.QWorkTime;
import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final QSchedule s = QSchedule.schedule;
    private final QDaySchedule ds = daySchedule;
    private final QWorkTime dswi = workTime;
    private final QLikeSchedule qls = likeSchedule;
    private final QWork w = QWork.work;
    private final QUser u = QUser.user;



    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    @Override
    public Page<ScheduleInfoDto> selectScheduleListByFilter(
            ScheduleSearchFilter scheduleSearchFilter, User loginUser) {
        JPAQuery<ScheduleInfoDto> query = createSelectFromQuery(loginUser);
        Pageable pageable = PageRequest.of(scheduleSearchFilter.getPage(),
                scheduleSearchFilter.getSize());

        if (scheduleSearchFilter.getType().equals(SearchType.MY) && loginUser != null) {
            query.where(equalUser(loginUser), containsKeyword(scheduleSearchFilter.getKeyword()));
        } else {
            query.where(containsKeyword(scheduleSearchFilter.getKeyword()));
        }

        long total = query.fetchCount();

        List<ScheduleInfoDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }
    public Page<ScheduleInfoDto> findLikeSchedulesByIds(List<Long> scheduleIds, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        JPAQuery<ScheduleInfoDto> query = queryFactory
                .select(Projections.constructor(ScheduleInfoDto.class,
                        s.id,
                        s.title,
                        s.user,
                        s.createdAt,

                        JPAExpressions.select(qls)
                                .from(qls)
                                .where(
                                        qls.schedule.eq(s),
                                        qls.user.eq(s.user)
                                )
                                .exists(),

                        JPAExpressions.select(qls.count())
                                .from(qls)
                                .where(qls.schedule.eq(s)) // 해당 스케줄에 대한 좋아요 개수를 가져옴
                ))
                .from(s)
                .where(s.id.in(scheduleIds)); // 좋아요한 스케줄 ID 목록으로 필터링

        long total = query.fetchCount();

        List<ScheduleInfoDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }

    private JPAQuery<ScheduleInfoDto> createSelectFromQuery(User loginUser) {
        return queryFactory
                .select(Projections.constructor(ScheduleInfoDto.class,
                        s.id,
                        s.title,
                        s.user,
                        s.createdAt,
                        // likeStatus를 계산하는 부분
                        JPAExpressions.select(qls)
                                .from(qls)
                                .where(
                                        qls.schedule.eq(s),
                                        qls.user.id.eq(loginUser.getId())
                                )
                                .exists(),
                        // likeCount를 계산하는 부분
                        JPAExpressions.select(qls.count())
                                .from(qls)
                                .where(qls.schedule.eq(s)) // 해당 스케줄에 대한 좋아요 개수를 가져옴
                ))
                .from(s);
    }

    BooleanExpression equalUser(User loginUser) {
        if (loginUser == null) {
            return null;
        }
        return s.user.eq(loginUser);
    }

    BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return s.title.contains(keyword);
    }

    @Override
    public Page<ScheduleInfoDto> getSchedulesOrderedByLikes(List<Long> scheduleIds, int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page, size);

        JPAQuery<ScheduleInfoDto> query = queryFactory
                .select(Projections.constructor(ScheduleInfoDto.class,
                        s.id,
                        s.title,
                        s.user,
                        s.createdAt,
                        // likeStatus: 로그인한 사용자가 해당 스케줄을 좋아했는지 여부
                        JPAExpressions.selectOne()
                                .from(qls)
                                .where(qls.schedule.eq(s).and(qls.user.id.eq(userId))) // 로그인 유저의 좋아요 상태 체크
                                .exists(),
                        // 좋아요 개수
                        JPAExpressions.select(qls.count())
                                .from(qls)
                                .where(qls.schedule.eq(s))
                ))
                .from(s)
                .leftJoin(qls).on(qls.schedule.eq(s))
                .where(s.id.in(scheduleIds)) // 주어진 scheduleIds에 해당하는 스케줄만 필터링
                .groupBy(s.id)
                .orderBy(qls.count().desc()) // 좋아요 개수로 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<ScheduleInfoDto> content = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    @Transactional
    public boolean deleteSchedule(Long userId, Long scheduleId) {

        queryFactory
                .delete(workTime)
                .where(workTime.daySchedule.schedule.id.eq(scheduleId))
                .execute();

        queryFactory
                .delete(likeSchedule)
                .where(likeSchedule.schedule.id.eq(scheduleId))
                .execute();

        queryFactory
                .delete(daySchedule)
                .where(daySchedule.schedule.id.eq(scheduleId))
                .execute();


        // 스케줄 삭제
        long deletedCount = queryFactory
                .delete(s)
                .where(s.id.eq(scheduleId)
                        .and(s.user.id.eq(userId)))
                .execute();

        return deletedCount > 0;
    }

    @Override
    @Transactional
    public boolean updateSchedule(Long userId, Long scheduleId, ScheduleRegisterDto ScheduleRegisterDto) {

        long updatedCount = queryFactory
                .update(s)
                .set(s.title, ScheduleRegisterDto.getTitle())
                .where(s.id.eq(scheduleId)
                        .and(s.user.id.eq(userId)))
                .execute();

        return updatedCount > 0;
    }

}
