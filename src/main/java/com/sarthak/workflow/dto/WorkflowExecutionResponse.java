package com.sarthak.workflow.dto;

import java.time.LocalDateTime;

public record WorkflowExecutionResponse(
        Long id,
        Long workflowId,
        String workflowName,
        String status,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
