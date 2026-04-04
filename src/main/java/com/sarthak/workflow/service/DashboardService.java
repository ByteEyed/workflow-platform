package com.sarthak.workflow.service;

import com.sarthak.workflow.dto.DashboardStatsResponse;
import com.sarthak.workflow.dto.ExecutionTrendResponse;
import com.sarthak.workflow.domain.enums.WorkflowStatus;
import com.sarthak.workflow.repository.WorkflowRepository;
import com.sarthak.workflow.repository.WorkflowExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final WorkflowRepository workflowRepository;
    private final WorkflowExecutionRepository executionRepository;

    public DashboardStatsResponse getStats() {
        long activeWorkflows = workflowRepository.countByStatus(WorkflowStatus.ACTIVE);
        long totalExecutions = executionRepository.count();
        long runningExecutions = executionRepository.countByStatus("RUNNING");
        long successfulExecutions = executionRepository.countByStatus("SUCCESS");
        long failedExecutions = executionRepository.countByStatus("FAILED");

        double successRate = totalExecutions > 0
                ? (double) successfulExecutions / totalExecutions * 100
                : 0;

        return new DashboardStatsResponse(
                activeWorkflows,
                runningExecutions,
                Math.round(successRate * 100.0) / 100.0,
                failedExecutions
        );
    }

    public List<ExecutionTrendResponse> getTrends() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd")
                .withZone(ZoneId.systemDefault());

        Instant now = Instant.now();
        List<ExecutionTrendResponse> trends = new ArrayList<>();

        for (int i = 6; i >= 0; i--) {
            Instant dayStart = now.minus(i, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS);
            Instant dayEnd = dayStart.plus(1, ChronoUnit.DAYS);

            long success = executionRepository.countByStatusAndStartedAtBetween("SUCCESS", dayStart, dayEnd);
            long failed = executionRepository.countByStatusAndStartedAtBetween("FAILED", dayStart, dayEnd);

            trends.add(new ExecutionTrendResponse(
                    formatter.format(dayStart),
                    success,
                    failed
            ));
        }

        return trends;
    }
}
