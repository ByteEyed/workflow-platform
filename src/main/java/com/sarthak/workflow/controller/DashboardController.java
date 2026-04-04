package com.sarthak.workflow.controller;

import com.sarthak.workflow.dto.DashboardStatsResponse;
import com.sarthak.workflow.dto.ExecutionTrendResponse;
import com.sarthak.workflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStatsResponse getStats() {
        return dashboardService.getStats();
    }

    @GetMapping("/trends")
    public List<ExecutionTrendResponse> getTrends() {
        return dashboardService.getTrends();
    }
}
