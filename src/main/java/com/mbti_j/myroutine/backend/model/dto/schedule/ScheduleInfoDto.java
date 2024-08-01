package com.mbti_j.myroutine.backend.model.dto.schedule;

import com.mbti_j.myroutine.backend.model.entity.User;
import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScheduleInfoDto {

    Long id;

    String title;

    User user;

    Date createdAt;

    public ScheduleInfoDto(Long id, String title, User user, Date createdAt) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.createdAt = createdAt;
    }
}
