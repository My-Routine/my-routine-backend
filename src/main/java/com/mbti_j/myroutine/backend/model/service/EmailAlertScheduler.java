package com.mbti_j.myroutine.backend.model.service;

import com.mbti_j.myroutine.backend.model.entity.User;
import com.mbti_j.myroutine.backend.model.entity.WorkTime;
import com.mbti_j.myroutine.backend.repository.WorkTimeRepository;
import jakarta.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailAlertScheduler {
    @Autowired
    private WorkTimeRepository workTimeRepository;

    @Autowired
    private EmailService emailService;

    // 매 1분마다 실행 (1분마다 WorkTime과 DaySchedule 테이블을 체크)
    @Scheduled(cron = "0 */1 * * * *")
    @Transactional
    public void checkAndSendAlerts() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        LocalTime tenMinutesLater = currentTime.plus(10, ChronoUnit.MINUTES);

        // 현재 요일을 가져옴 (1=월요일, ..., 7=일요일)
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int currentDayOfWeek = convertDayOfWeekToNumber(dayOfWeek);

        // 현재 요일과 시간이 맞는 WorkTime 가져오기 (startAt 기준)
        List<WorkTime> upcomingWorkTimes = workTimeRepository
                .findAllByDaySchedule_DayAndStartAtBetween(currentDayOfWeek, currentTime, tenMinutesLater);

        // 알림 전송
        for (WorkTime workTime : upcomingWorkTimes) {
            sendEmailAlert(workTime);
        }
    }

    private void sendEmailAlert(WorkTime workTime) {
        User user = workTime.getDaySchedule().getSchedule().getUser();
        String userEmail = user.getEmail();

        String subject = "일정 알림";
        String text = "작업 '" + workTime.getWork().getTitle() + "'이 곧 시작됩니다. 시작 시간: " + workTime.getStartAt();

        emailService.sendSimpleEmail(userEmail, subject, text);
    }

    private int convertDayOfWeekToNumber(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return 1;
            case TUESDAY: return 2;
            case WEDNESDAY: return 3;
            case THURSDAY: return 4;
            case FRIDAY: return 5;
            case SATURDAY: return 6;
            case SUNDAY: return 7;
            default: throw new IllegalArgumentException("Invalid DayOfWeek: " + dayOfWeek);
        }
    }
}

