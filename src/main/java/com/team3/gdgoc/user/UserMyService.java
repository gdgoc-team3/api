package com.team3.gdgoc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMyService {
    private final UserMyRepository userMyRepository;
}
