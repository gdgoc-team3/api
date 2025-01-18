package com.team3.gdgoc.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskCheckStatusRepository extends JpaRepository<TaskCheckStatusEntity, Long> {

    List<TaskCheckStatusEntity> findAllByTaskIdIn(List<Long> taskId);
}
