package com.team3.gdgoc.ai.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class FeedbackDateTimeInfoResponse {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    @Builder
    private FeedbackDateTimeInfoResponse(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
