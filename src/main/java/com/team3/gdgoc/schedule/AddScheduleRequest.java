package com.team3.gdgoc.schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddScheduleRequest {

    private String title;

    private String startDate;

    private String endDate;

    private String mustDoTasks;

    private String requirements;

    @Builder
    public AddScheduleRequest(String title, String startDate, String endDate, String mustDoTasks, String requirements) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mustDoTasks = mustDoTasks;
        this.requirements = requirements;
    }
}
