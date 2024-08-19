package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.constant.SearchType;
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

@Repository
@AllArgsConstructor
public class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;
    private final QSchedule s = QSchedule.schedule;
    private final QDaySchedule ds = QDaySchedule.daySchedule;
    private final QWorkTime dswi = QWorkTime.workTime;
    private final QLikeSchedule qls = QLikeSchedule.likeSchedule;
    private final QWork w = QWork.work;
    private final QUser u = QUser.user;

//    @Override
//    public ScheduleDetailDto searchScheduleDetailById(Long id) {
//        // Schedule 데이터 가져오기
//        Schedule schedule = queryFactory.selectFrom(s)
//                .leftJoin(s.user, u).fetchJoin()
//                .where(s.id.eq(id))
//                .fetchOne();
//
//        // daySchedules 가져오기
//        List<DaySchedule> daySchedules = queryFactory.selectFrom(ds)
//                .where(ds.schedule.id.eq(schedule.getId()))
//                .fetch();
//
//        // 각 daySchedule에 대해 dayScheduleWorkInfo 가져오기
//        List<DayScheduleDetailDto> dayScheduleDetails = daySchedules.stream().map(daySchedule -> {
//            List<WorkTimeDto> dayScheduleWorkInfos = queryFactory.select(
//                            Projections.constructor(WorkTimeDto.class,
//                                    dswi.id,
//                                    w.title,
//                                    dswi.startAt,
//                                    dswi.endAt
//                            ))
//                    .from(dswi)
//                    .leftJoin(dswi.work, w)
//                    .where(dswi.daySchedule.id.eq(daySchedule.getId()))
//                    .fetch();
//
//            return new DayScheduleDetailDto(
//                    daySchedule.getId(),
//                    daySchedule.getDay()
//            );
//        }).collect(Collectors.toList());
//
//        // UserDTO로 변환
//        UserProfileDto userProfileDto = new UserProfileDto(schedule.getUser().getNickname(),
//                schedule.getUser().getImg());
//
//        // ScheduleDetailDto로 변환
//        return new ScheduleDetailDto(
//                schedule.getId(),
//                schedule.getTitle(),
//                userProfileDto,
//                schedule.getCreatedAt(),
//                dayScheduleDetails
//        );
//
//
//    }

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

    private JPAQuery<ScheduleInfoDto> createSelectFromQuery(User loginUser) {
        return queryFactory
                .select(Projections.constructor(ScheduleInfoDto.class,
                        s.id,
                        s.title,
                        s.user,
                        s.createdAt,
                        JPAExpressions.select(qls)
                                .from(qls)
                                .where(
                                        qls.schedule.eq(s),
                                        qls.user.id.eq(loginUser.getId())
                                )
                                .exists())
                )
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


}
