package com.team3.gdgoc.ai;

import com.team3.gdgoc.ai.client.AiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final AiClient aiClient;

    public String getData(String query) {
        return aiClient.getData(query);
    }

}
