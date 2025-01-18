package com.team3.gdgoc.schedule;

import com.team3.gdgoc.ai.AiService;
import com.team3.gdgoc.ai.GetAiScheduleRequest;
import com.team3.gdgoc.ai.client.FeedbackDateTimeInfoResponse;
import com.team3.gdgoc.ai.client.FeedbackResponse;
import com.team3.gdgoc.ai.client.FeedbackTaskResponse;
import com.team3.gdgoc.interest.InterestEntity;
import com.team3.gdgoc.interest.InterestService;
import com.team3.gdgoc.task.TaskDateResponse;
import com.team3.gdgoc.task.TaskEntity;
import com.team3.gdgoc.task.TaskResponse;
import com.team3.gdgoc.task.TaskService;
import com.team3.gdgoc.user.UserInfoResponse;
import com.team3.gdgoc.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        Long interestId = interestService.getInterestByUserId(user.getUserId()).getId();

        InterestEntity interest = interestService.getInterest(interestId);

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
                .createdAt(LocalDateTime.now())
                .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        GetAiScheduleRequest getAiScheduleRequest = GetAiScheduleRequest.builder()
                .birthDate(String.valueOf(userInfo.getBirthDate()))
                .major(interest.getMajor())
                .desiredJob(interest.getDesiredJob())
                .targetEmploymentPeriod(String.valueOf(interest.getTargetEmploymentPeriod()))
                .scheduleTitle(scheduleEntity.getTitle())
                .scheduleStartDate(formatter.format(scheduleEntity.getStartDate()))
                .scheduleEndDate(formatter.format(scheduleEntity.getEndDate()))
                .mustDoTasks(scheduleEntity.getMustDoTasks())
                .requirements(scheduleEntity.getScheduleRequirements())
                .build();

        FeedbackResponse getSchedules = aiService.getSchedules(getAiScheduleRequest);

        List<FeedbackTaskResponse> feedbackTasks = getSchedules.getTasks();

        ScheduleEntity saved = scheduleRepository.save(scheduleEntity);

        Long scheduleId = saved.getId();

        List<TaskEntity> tasks = feedbackTasks.stream()
                .map(task -> {

                    FeedbackDateTimeInfoResponse feedbackStartDate = task.getStartDate();

                    FeedbackDateTimeInfoResponse feedbackEndDate = task.getEndDate();

                    return TaskEntity.builder()
                            .scheduleId(scheduleId)
                            .title(task.getTitle())
                            .startTime(feedbackStartDate.toLocalDateTime())
                            .endTime(feedbackEndDate.toLocalDateTime())
                            .createdAt(LocalDateTime.now())
                            .build();
                })
                .toList();

        taskService.addTasks(tasks);

        List<TaskResponse> taskResponses = tasks.stream()
                .map(task -> TaskResponse.builder()
                        .taskId(Math.toIntExact(task.getId()))
                        .title(task.getTitle())
                        .isCompleted(false)
                        .startDate(TaskDateResponse.builder()
                                    .year(task.getStartTime().getYear())
                                    .month(task.getStartTime().getMonthValue())
                                    .day(task.getStartTime().getDayOfMonth())
                                    .hour(task.getStartTime().getHour())
                                    .minute(task.getStartTime().getMinute())
                                    .build()
                        )
                        .endDate(TaskDateResponse.builder()
                                .year(task.getEndTime().getYear())
                                .month(task.getEndTime().getMonthValue())
                                .day(task.getEndTime().getDayOfMonth())
                                .hour(task.getEndTime().getHour())
                                .minute(task.getEndTime().getMinute())
                                .build())
                        .build())
                .toList();

        return ScheduleResponse.builder()
                .scheduleId(Math.toIntExact(saved.getId()))
                .title(saved.getTitle())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .mustDoTasks(saved.getMustDoTasks())
                .requirements(saved.getScheduleRequirements())
                .tasks(taskResponses)
                .build();
    }

    Boolean hasSchedule(String userIdentity, String dateString) {

        UserInfoResponse user = userService.getUserInfoByIdentity(userIdentity);

        List<ScheduleEntity> scheduleList = scheduleRepository.findAllByUserId(user.getUserId());

        LocalDate now = LocalDate.parse(dateString);

        for (ScheduleEntity schedule : scheduleList) {
            if (!now.isBefore(schedule.getStartDate()) && !now.isAfter(schedule.getEndDate())) {
                return true;
            }
        }

        return false;
    }

    public List<ScheduleEntity> getScheduleList() {
        return scheduleRepository.findAll();
    }
}
