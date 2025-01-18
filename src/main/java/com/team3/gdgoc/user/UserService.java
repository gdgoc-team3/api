package com.team3.gdgoc.user;

import com.team3.gdgoc.interest.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final InterestService interestService;


    public UserEntity createUser(UserInfoResponse userInfoResponse) {
        UserEntity userEntity = userRepository.save(UserEntity.builder()
                .identity(userInfoResponse.getUserIdentity())
                .birthDate(userInfoResponse.getBirthDate())
                .nickname(userInfoResponse.getNickname())
                .build());
        interestService.createInterestForUser(userEntity.getId(), userInfoResponse.getMajor(), userInfoResponse.getDesiredJob(), userInfoResponse.getTargetEmploymentPeriod());
        return userEntity;
    }
}
