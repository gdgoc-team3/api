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
}
