package com.mbti_j.myroutine.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "like_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_from_id", "user_to_id"})
})
public class LikeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private User userTo;

}