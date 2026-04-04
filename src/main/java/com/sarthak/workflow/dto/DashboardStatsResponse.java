package com.sarthak.workflow.dto;

public record DashboardStatsResponse(
        long activeWorkflows,
        long runningExecutions,
        double successRate,
        long failedTasks
) {}
