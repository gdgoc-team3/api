package com.team3.gdgoc.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllByScheduleId(Long scheduleId);

    // 특정 사용자가 담당한 task 목록을 반환
    List<TaskEntity> findByScheduleId(Long scheduleId);
}
