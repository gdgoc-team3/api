package com.team3.gdgoc.demo;

import com.team3.gdgoc.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public ApiResponse<String> hello() {
        return ApiResponse.success("Hello, world!");
    }

}
