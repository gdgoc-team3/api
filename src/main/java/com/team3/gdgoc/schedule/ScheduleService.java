package com.team3.gdgoc.schedule;

import com.team3.gdgoc.ai.AiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final AiService aiService;

    @Transactional
    public ScheduleResponse addSchedule(AddScheduleRequest request) {

        int interestId = 1;

        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .userIdentity(request.getUserIdentity())
                .interestId((long) interestId)
                .title(request.getTitle())
                .startDate(startDate)
                .endDate(endDate)
                .mustDoTasks(request.getMustDoTasks())
                .scheduleRequirements(request.getRequirements())
                .build();

        ScheduleEntity saved = scheduleRepository.save(scheduleEntity);

        return ScheduleResponse.builder()
                .scheduleId(Math.toIntExact(saved.getId()))
                .title(saved.getTitle())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .mustDoTasks(saved.getMustDoTasks())
                .requirements(saved.getScheduleRequirements())
                .tasks(List.of())
                .build();
    }

    public List<ScheduleEntity> getScheduleList() {
        return scheduleRepository.findAll();
    }
}
