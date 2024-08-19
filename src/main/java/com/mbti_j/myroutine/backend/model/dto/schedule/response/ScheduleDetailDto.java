package com.mbti_j.myroutine.backend.model.dto.schedule.response;

import com.mbti_j.myroutine.backend.model.dto.user.UserProfileDto;
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
@AllArgsConstructor
@ToString
@Builder
public class ScheduleDetailDto {

    private Long id;
    private String title;
    private UserProfileDto userProfileDto;
    private Date createdAt;
    private Boolean likeStatus;
}
