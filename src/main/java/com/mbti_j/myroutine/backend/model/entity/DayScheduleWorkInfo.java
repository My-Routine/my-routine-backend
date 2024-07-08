package com.mbti_j.myroutine.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Time;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@ToString
@Entity
public class DayScheduleWorkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "day_schedule_id")
    private DaySchedule daySchedule;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    @DateTimeFormat
    private Time startAt;

    @DateTimeFormat
    private Time endAt;

    @ManyToOne
    @JoinColumn(name = "alert_type_id")
    private AlertType alertType;
}
