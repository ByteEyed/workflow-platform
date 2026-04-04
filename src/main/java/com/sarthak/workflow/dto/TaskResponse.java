package com.sarthak.workflow.dto;

import com.sarthak.workflow.domain.enums.TaskType;

public record TaskResponse(
        Long id,
        Long workflowId,
        String name,
        TaskType type,
        int retryCount,
        int timeoutSeconds
) {}
