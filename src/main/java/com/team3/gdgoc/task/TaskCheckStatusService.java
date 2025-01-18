package com.team3.gdgoc.task;


import com.team3.gdgoc.schedule.ScheduleEntity;
import com.team3.gdgoc.schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskCheckStatusService {

    private final TaskCheckStatusRepository taskCheckStatusRepository;
    private final TaskRepository taskRepository;
    private final ScheduleRepository scheduleRepository;

    // 전체 task 개수 가져오기
    public long getTotalTaskCount(List<Long> taskIds) {
        List<TaskCheckStatusEntity> taskCheckStatusList = taskCheckStatusRepository.findAllByTaskIdIn(taskIds);
        return taskCheckStatusList.size(); // 리스트의 크기 = 전체 task 개수
    }
    // 완료된 task 개수 가져오기
    public long getCompletedTaskCount(List<Long> taskIds) {
        List<TaskCheckStatusEntity> taskCheckStatusList = taskCheckStatusRepository.findAllByTaskIdIn(taskIds);

        return taskCheckStatusList.stream()
                .filter(TaskCheckStatusEntity::getIsCompleted)  // 완료된 task만 필터링
                .count(); // 완료된 task 개수
    }

    // 특정 task에 대해 진행률을 사용자에게 보여줄 수 있게 처리
    @Transactional
    public double getTaskCompletionRateForUser(Long userId, Long scheduleId) {
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found for userId: " + userId));

            List<TaskEntity> tasks = taskRepository.findByScheduleId(schedule.getId());


        // task의 ID들을 리스트로 가져옴
        List<Long> taskIds = tasks.stream()
                .map(TaskEntity::getId)
                .collect(Collectors.toList());

        // 완료된 task 개수 계산
        long completedTasks = getCompletedTaskCount(taskIds);

//        long totalTasks = getTotalTaskCount(taskIds);
        long totalTasks = taskIds.size();

        // 진행률 계산
        if (totalTasks == 0) {
            return 0; // 작업이 없을 경우 진행률 0%
        }

        double completionRate = (double) completedTasks / totalTasks * 100;
        return (int) Math.floor(completionRate); // 소숫점을 버리고 정수로 반환
    }

}
