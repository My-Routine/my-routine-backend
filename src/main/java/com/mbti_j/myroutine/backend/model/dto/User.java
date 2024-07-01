package com.mbti_j.myroutine.backend.model.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private long id;
    private String username;
    private String passwordHash;
    private String email;
    private String phone;
    private String img;
    private Date createdAt;
    private Date deletedAt;
    private String token;
}
