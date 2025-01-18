package com.team3.gdgoc.ai.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "aiClient", url = "https://api.example.com")
public interface AiClient {
    @GetMapping("/data")
    String getData(@RequestParam String query);
}
