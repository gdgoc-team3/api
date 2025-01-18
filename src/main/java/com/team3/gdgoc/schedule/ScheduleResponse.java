package com.team3.gdgoc.schedule;

import com.team3.gdgoc.task.TaskResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleResponse {

    private int scheduleId;

    private String title;

    private String startDate;

    private String endDate;

    private String mustDoTasks;

    private String requirements;

    private List<TaskResponse> tasks;

    @Builder
    public ScheduleResponse(int scheduleId, String title, String startDate, String endDate, String mustDoTasks, String requirements, List<TaskResponse> tasks) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mustDoTasks = mustDoTasks;
        this.requirements = requirements;
        this.tasks = tasks;
    }
}
