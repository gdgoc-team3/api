package com.team3.gdgoc.task;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskDateResponse {

    private int year;

    private int month;

    private int day;

    private int hour;

    private int minute;

    @Builder
    private TaskDateResponse(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
}
