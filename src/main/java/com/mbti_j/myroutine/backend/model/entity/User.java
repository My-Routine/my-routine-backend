package com.mbti_j.myroutine.backend.model.entity;

import com.mbti_j.myroutine.backend.model.dto.user.UserSignUpDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class) // Listener
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true)
    private String nickname;

    @Column(length = 64)
    private String passwordHash;

    @Column(length = 30, unique = true)
//    @Email
    private String email;

    @Column(length = 11)
    private String phone; // 010-5092-6683 X 01050926683 O

    @Column(length = 255)
    private String img;

    @Column(length = 15)
    @CreatedDate
    private Timestamp createdAt;

    @Column(length = 15)
    private Timestamp deletedAt;

    @Column(length = 255)
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Participants> chatRooms; // 사용자가 참여한 채팅방 리스트

    public User() {
    }

    @Builder
    public User(String nickname, String passwordHash, String email, String phone, String img,
            String token) {
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
        this.img = img;
        this.token = token;
    }


    public User signUpDtoToEntity(UserSignUpDto dto) {
        return User.builder()
                .nickname(dto.getNickname())
                .passwordHash(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .img(dto.getImg())
                .build();
    }

    public void updateRefreshToken(String refreshToken) {
        this.token = refreshToken;
    }

    public void updateDeletedAt(Timestamp now) {
        this.deletedAt = now;
    }
}
