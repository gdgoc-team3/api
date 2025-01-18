package com.team3.gdgoc.schedule;

import lombok.Builder;

@Builder
public class AddScheduleRequest {

    private String title;

    private String startDate;

    private String endDate;

    private String mustDoTasks;

    private String requirements;
}
