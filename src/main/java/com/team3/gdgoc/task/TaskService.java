package com.team3.gdgoc.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskEntity> addTasks(List<TaskEntity> tasks) {
        return taskRepository.saveAll(tasks);
    }
}
