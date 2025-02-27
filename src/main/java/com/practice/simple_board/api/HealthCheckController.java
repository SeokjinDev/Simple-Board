package com.practice.simple_board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check", description = "서버 상태 확인 API 입니다.")
@RestController
public class HealthCheckController {
    @Operation(
            summary = "Health Check API",
            description = "서버 상태 확인 API 입니다."
    )
    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}
