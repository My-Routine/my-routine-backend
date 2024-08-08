package com.mbti_j.myroutine.backend.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeConverter {

    public static Time convertStringToSqlTime(String timeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            // Date 객체로 변환한 뒤 Time으로 변환
            long ms = sdf.parse(timeString).getTime();
            return new Time(ms);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null; // 변환 실패 시 null 반환
        }
    }

    public static void main(String[] args) {
        String timeString = "12:00";
        Time sqlTime = convertStringToSqlTime(timeString);
        System.out.println("SQL Time: " + sqlTime);
    }
}
