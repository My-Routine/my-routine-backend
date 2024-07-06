package com.mbti_j.myroutine.backend.model.entity;

import com.mbti_j.myroutine.backend.model.dto.UserInfoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10)
    private String username;

    @Column(length = 64)
    private String passwordHash;

    @Column(length = 30)
    @Email
    private String email;

    @Column(length = 11)
    private String phone; // 010-5092-6683 X 01050926683 O

    @Column(length = 20)
    private String img;

    @Column(length = 15)
    private Date createdAt;

    @Column(length = 15)
    private Date deletedAt;

    @Column(length = 15)
    private String token;

    public UserInfoDto toUserInfoDto() {

        return UserInfoDto.builder()
                .id(this.getId())
                .email(this.getEmail())
                .img(this.getImg())
                .phone(this.getPhone())
                .username(this.getUsername()).build();
    }
}
