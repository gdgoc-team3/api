package com.team3.gdgoc.user;

import com.team3.gdgoc.common.ApiResponse;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    private final UserService userService;

    @GetMapping("/{userIdentity}")
    public ApiResponse<UserInfoResponse> getUserInfo(@PathVariable String userIdentity) {
        UserInfoResponse response = UserInfoResponse.builder()
                .birthDate(LocalDate.of(1998, 1, 1))
                .nickname("개발자")
                .userIdentity(userIdentity)
                .major("컴퓨터과학과")
                .desiredJob("백엔드")
                .targetEmploymentPeriod(3)
                .build();

        return ApiResponse.success(response);
    }
    @PostMapping("")
    public ApiResponse<UserInfoResponse> addUser(@RequestBody AddUserRequest request) {
        UserInfoResponse response = UserInfoResponse.builder()
                .birthDate(request.getBirthDate())
                .nickname(request.getNickname())
                .userIdentity(request.getUserIdentity())
                .major(request.getMajor())
                .desiredJob(request.getDesiredJob())
                .targetEmploymentPeriod(request.getTargetEmploymentPeriod())
                .build();

        return ApiResponse.success(response);
    }

}
