package com.mbti_j.myroutine.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.sql.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class) // Listener
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 20)
    private String title;

    @Column(length = 20)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany
    @JoinColumn(name = "schedule_id")
    private List<DaySchedule> daySchedules;

    private Boolean visibility;

    // default : 0
    private Long hit;

    @Builder
    public Schedule(Long id, User user, String title, List<DaySchedule> daySchedule,
            Date createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.createdAt = createdAt;
    }

}
