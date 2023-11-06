package com.ticketexchange.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketexchange.support.web.ApiResult;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

    @GetMapping
    public ResponseEntity<ApiResult<String>> health() {
        log.info("health check");
        return ResponseEntity.ok(ApiResult.succeed("OK"));
    }
}
