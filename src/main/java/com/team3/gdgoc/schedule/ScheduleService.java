package com.team3.gdgoc.schedule;

import com.team3.gdgoc.ai.AiService;
import com.team3.gdgoc.interest.InterestService;
import com.team3.gdgoc.task.TaskService;
import com.team3.gdgoc.user.UserEntity;
import com.team3.gdgoc.user.UserInfoResponse;
import com.team3.gdgoc.user.UserService;
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

    private final TaskService taskService;

    private final UserService userService;

    private final InterestService interestService;

    @Transactional
    public ScheduleResponse addSchedule(AddScheduleRequest request) {

        UserInfoResponse user = userService.getUserInfoByIdentity(request.getUserIdentity());

        Long interestId =interestService.getInterestByUserId(user.getUserId()).getId();

        LocalDate startDate = LocalDate.parse(request.getStartDate());
        LocalDate endDate = LocalDate.parse(request.getEndDate());

        UserInfoResponse userInfo = userService.getUserInfoByIdentity(request.getUserIdentity());

        ScheduleEntity scheduleEntity = ScheduleEntity.builder()
                .userId(userInfo.getUserId())
                .interestId(interestId)
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
