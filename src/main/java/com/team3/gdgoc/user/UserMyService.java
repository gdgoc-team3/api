package com.team3.gdgoc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMyService {

    private final UserMyRepository userMyRepository;

    public String getNickname(String identity) {
        return userMyRepository.findByIdentity(identity)
                .map(UserEntity::getNickname) // nickname만 가져옴
                .orElseThrow(() -> new IllegalArgumentException("User not found with identity: " + identity));
    }
    // identity로 userId 가져오기
    public Long getUserId(String identity) {
        return userMyRepository.findByIdentity(identity)
                .map(UserEntity::getId) // userId만 가져옴
                .orElseThrow(() -> new IllegalArgumentException("User not found with identity: " + identity));
    }
}
