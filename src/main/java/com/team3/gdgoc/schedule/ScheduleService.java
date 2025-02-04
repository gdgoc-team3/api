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
import com.team3.gdgoc.user.MonthlyProgressResponse;
import com.team3.gdgoc.user.UserInfoResponse;
import com.team3.gdgoc.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<ScheduleResponse> getScheduleList(String userIdentity, String year, String month) {

        UserInfoResponse user = userService.getUserInfoByIdentity(userIdentity);

        List<ScheduleEntity> scheduleList = scheduleRepository.findAllByUserId(user.getUserId());

        // 입력받은 year, month를 기반으로 해당 월의 시작일과 마지막일을 계산
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);

        LocalDate startOfMonth = LocalDate.of(yearInt, monthInt, 1);
        LocalDate endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.lengthOfMonth());

        // 해당 기간에 걸쳐 있는 일정 필터링
        List<ScheduleEntity> filteredSchedules = scheduleList.stream()
                .filter(schedule ->
                        !(schedule.getEndDate().isBefore(startOfMonth) || schedule.getStartDate().isAfter(endOfMonth))
                )
                .toList();

        return filteredSchedules.stream()
                .map(schedule -> {

                    List<TaskEntity> tasks = taskService.getTasksByScheduleId(schedule.getId());

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
                            .scheduleId(Math.toIntExact(schedule.getId()))
                            .title(schedule.getTitle())
                            .startDate(schedule.getStartDate().toString())
                            .endDate(schedule.getEndDate().toString())
                            .mustDoTasks(schedule.getMustDoTasks())
                            .requirements(schedule.getScheduleRequirements())
                            .tasks(taskResponses)
                            .build();
                })
                .toList();
    }

    public List<MonthlyProgressResponse> getMonthlyProgress(String userIdentity, String year, String month) {
        UserInfoResponse user = userService.getUserInfoByIdentity(userIdentity);
        List<ScheduleEntity> scheduleList = scheduleRepository.findAllByUserId(user.getUserId());

        int targetYear = Integer.parseInt(year);
        int targetMonth = Integer.parseInt(month);

        // 해당 월의 전체 일 수 가져오기
        YearMonth yearMonth = YearMonth.of(targetYear, targetMonth);
        int daysInMonth = yearMonth.lengthOfMonth();

        // 기본적으로 모든 날짜의 progress를 0으로 초기화
        Map<Integer, Integer> progressMap = new HashMap<>();
        for (int day = 1; day <= daysInMonth; day++) {
            progressMap.put(day, 0);
        }

        Random random = new Random();

        // 일정이 있는 날짜에 progress 값을 랜덤으로 설정
        for (ScheduleEntity schedule : scheduleList) {
            LocalDate startDate = schedule.getStartDate();
            LocalDate endDate = schedule.getEndDate();

            // 일정이 해당 월에 속하는 경우만 처리
            if ((startDate.getYear() == targetYear && startDate.getMonthValue() == targetMonth) ||
                    (endDate.getYear() == targetYear && endDate.getMonthValue() == targetMonth) ||
                    (startDate.isBefore(yearMonth.atEndOfMonth()) && endDate.isAfter(yearMonth.atDay(1)))) {

                LocalDate current = startDate;
                while (!current.isAfter(endDate) && current.getMonthValue() == targetMonth) {
                    int day = current.getDayOfMonth();
                    progressMap.put(day, random.nextInt(101)); // 0~100 랜덤 값
                    current = current.plusDays(1);
                }
            }
        }

        // 결과 리스트 생성
        List<MonthlyProgressResponse> responseList = new ArrayList<>();
        for (int day = 1; day <= daysInMonth; day++) {
            responseList.add(MonthlyProgressResponse.builder()
                    .day(day)
                    .progress(progressMap.get(day))
                    .build());
        }

        return responseList;
    }

    public ScheduleEntity getScheduleByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }
}
