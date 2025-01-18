package com.team3.gdgoc.interest;

import com.team3.gdgoc.user.UserEntity;
import com.team3.gdgoc.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;
    private final UserRepository userRepository;

    public InterestEntity getInterestByUserId(Long userId) {
        List<InterestEntity> interestEntities = interestRepository.findByUserId(userId);
        if (interestEntities.isEmpty()) {
            return null;
        }
        return interestEntities.getFirst();
    }

    @Transactional
    public InterestEntity createInterest(Long userId, String major, String desiredJob, int targetEmploymentPeriod) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음"));

        InterestEntity interest = InterestEntity.builder()
                .userId(user.getId())
                .major(major)
                .desiredJob(desiredJob)
                .targetEmploymentPeriod(targetEmploymentPeriod)
                .createdAt(LocalDateTime.now())
                .build();

        return interestRepository.save(interest);
    }

}
