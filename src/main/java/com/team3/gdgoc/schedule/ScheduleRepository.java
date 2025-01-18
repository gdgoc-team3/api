package com.team3.gdgoc.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    // userId를 기준으로 스케줄 목록을 조회
    List<ScheduleEntity> findAllByUserId(Long userId);
    Optional<ScheduleEntity> findFirstByUserId(Long userId);

    ScheduleEntity findByUserId(Long userId);
}
