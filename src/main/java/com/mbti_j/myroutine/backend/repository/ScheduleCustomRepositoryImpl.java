package com.mbti_j.myroutine.backend.repository;

import com.mbti_j.myroutine.backend.model.constant.SearchType;
import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleInfoDto;
import com.mbti_j.myroutine.backend.model.dto.schedule.ScheduleSearchFilter;
import com.mbti_j.myroutine.backend.model.entity.QSchedule;
import com.mbti_j.myroutine.backend.model.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final QSchedule s = QSchedule.schedule;

    @Override
    public Page<ScheduleInfoDto> selectScheduleListByFilter(
            ScheduleSearchFilter scheduleSearchFilter, User loginUser) {
        JPAQuery<ScheduleInfoDto> query = createSelectFromQuery();
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

    private JPAQuery<ScheduleInfoDto> createSelectFromQuery() {
        return queryFactory
                .select(Projections.constructor(ScheduleInfoDto.class,
                        s.id,
                        s.title,
                        s.user,
                        s.createdAt))
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
