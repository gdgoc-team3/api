package com.team3.gdgoc.user;

import com.team3.gdgoc.interest.InterestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final InterestService interestService;

    public UserInfoResponse getUserInfoByIdentity(String identity) {
        UserEntity userEntity = userRepository.findByIdentity(identity);
        if (userEntity == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        return UserInfoResponse.builder()
                .userId(userEntity.getId())
                .identity(userEntity.getIdentity())
                .birthDate(userEntity.getBirthDate())
                .nickname(userEntity.getNickname())
                .build();
    }

    @Transactional
    public UserInfoResponse createUser(AddUserRequest request) {

        UserEntity alreadyExist = userRepository.findByIdentity(request.getUserIdentity());
        if (alreadyExist != null) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        UserEntity userEntity = UserEntity.builder()
                .identity(request.getUserIdentity())
                .birthDate(request.getBirthDate())
                .nickname(request.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        UserEntity saved = userRepository.save(userEntity);

        interestService.createInterest(
                saved.getId(),
                request.getMajor(),
                request.getDesiredJob(),
                request.getTargetEmploymentPeriod()
        );

        return UserInfoResponse.builder()
                .userId(userEntity.getId())
                .identity(userEntity.getIdentity())
                .birthDate(userEntity.getBirthDate())
                .nickname(userEntity.getNickname())
                .major(request.getMajor())
                .desiredJob(request.getDesiredJob())
                .targetEmploymentPeriod(request.getTargetEmploymentPeriod())
                .build();
    }
}
