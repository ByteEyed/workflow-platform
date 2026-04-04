package com.sarthak.workflow.dto;

import com.sarthak.workflow.domain.enums.ExecutionStatus;

import java.time.LocalDateTime;

public record TaskExecutionResponse(
        Long id,
        Long taskId,
        String taskName,
        Long workflowExecutionId,
        ExecutionStatus status,
        int attemptNumber,
        String logOutput,
        LocalDateTime startedAt,
        LocalDateTime finishedAt
) {}
