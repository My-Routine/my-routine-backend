package com.mbti_j.myroutine.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Time;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@NoArgsConstructor
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_schedule_id")
    private DaySchedule daySchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    private Time startAt;

    private Time endAt;

    @ManyToOne
    @JoinColumn(name = "alert_type_id")
    private AlertType alertType;

    @Builder
    public WorkTime(Long id, DaySchedule daySchedule, Work work, Time startAt,
            Time endAt, AlertType alertType) {
        this.id = id;
        this.daySchedule = daySchedule;
        this.work = work;
        this.startAt = startAt;
        this.endAt = endAt;
        this.alertType = alertType;
    }
}
