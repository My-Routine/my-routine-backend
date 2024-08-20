package com.mbti_j.myroutine.backend.model.dto.schedule.response;

import com.mbti_j.myroutine.backend.model.entity.User;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ScheduleInfoDto {

    private Long id;
    private String title;
    private User user;
    private Date createdAt;
    private Boolean likeStatus;
    private Long likeCount;


}
