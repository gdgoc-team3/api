package com.team3.gdgoc.user;

import com.team3.gdgoc.common.ApiResponse;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    private final UserService userService;

    @GetMapping("")
    public ApiResponse<UserInfoResponse> getUserInfo() {
        UserInfoResponse response = UserInfoResponse.builder()
                .birthDate(LocalDate.of(1998, 1, 1))
                .nickname("개발자")
                .userIdentity("user12324")
                .major("컴퓨터과학과")
                .desiredJob("백엔드")
                .targetEmploymentPeriod(3)
                .build();

        return ApiResponse.success(response);
    }

}
