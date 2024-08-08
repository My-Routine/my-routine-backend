package com.mbti_j.myroutine.backend.model.dto.schedule.response;

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

    private Long id;
    private String title;
    private User user;
    private Date createdAt;

    public ScheduleInfoDto(Long id, String title, User user, Date createdAt) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.createdAt = createdAt;
    }
}
