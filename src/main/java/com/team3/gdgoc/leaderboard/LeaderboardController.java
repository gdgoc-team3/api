package com.team3.gdgoc.leaderboard;

import com.team3.gdgoc.common.ApiResponse;
import com.team3.gdgoc.user.UserEntity;
import com.team3.gdgoc.user.UserInfoResponse;
import com.team3.gdgoc.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
    private static final Logger log = LoggerFactory.getLogger(LeaderboardController.class);
    private final LeaderboardService leaderboardService;
    private final UserService userService;

    @PostMapping("")
    public ApiResponse<LeaderboardResponse> addLeaderboard(@RequestBody LeaderboardRequest request){
        return ApiResponse.success(leaderboardService.addLeaderboard(request));
    }
    // 매칭하기 post
    // ai 콜을 해서 매칭
    //interestgroup 에 인서트를 하고, 리더보드 테이블에 interestgroup id 를 넣어준다.
    //
    //user_id 를 받아서

    @GetMapping("/{userId}")
    public ApiResponse<LeaderboardResponse> getLeaderboard(@PathVariable Long userId){
        UserInfoResponse user = userService.getUserInfoById(userId);
        log.info("user: {}", user.getIdentity());
        LeaderboardEntity leaderboardEntity = leaderboardService.getLeaderboard(user.getIdentity());
        return ApiResponse.success(LeaderboardResponse.builder()
                .interestGroupId(leaderboardEntity.getInterestGroupId())
                .processRatio(leaderboardEntity.getProcessRatio())
                .ranking(leaderboardEntity.getRanking())
                .userIdentity(leaderboardEntity.getUserIdentity())
                .build());
    }
}
