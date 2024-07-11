package com.mbti_j.myroutine.backend.model.dto.request;

import com.mbti_j.myroutine.backend.model.entity.Schedule;
import com.mbti_j.myroutine.backend.repository.UserRepository;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@RequiredArgsConstructor
public class ScheduleInfoDto {

    private final UserRepository userRepository;

    private Long id;
    private Long userId;
    private String title;
    private Date createdAt;

    public Schedule toScheduleInfoEntity() {

        return Schedule.builder()
//                .user(userInfoDto)
                .title(this.title)
                .createdAt(this.createdAt)
                .build();
    }
}
