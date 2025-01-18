package com.team3.gdgoc.task;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskResponse {

    private int taskId;

    private String title;

    private Boolean isCompleted;

    private TaskDateResponse startDate;

    private TaskDateResponse endDate;

    @Builder
    public TaskResponse(int taskId, String title, Boolean isCompleted, TaskDateResponse startDate, TaskDateResponse endDate) {
        this.taskId = taskId;
        this.title = title;
        this.isCompleted = isCompleted;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
