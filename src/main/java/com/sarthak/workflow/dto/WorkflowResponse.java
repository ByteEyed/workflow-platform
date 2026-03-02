package com.sarthak.workflow.dto;

import com.sarthak.workflow.domain.enums.WorkflowStatus;

import java.time.LocalDateTime;

public record WorkflowResponse(
        Long id,
        String name,
        String description,
        WorkflowStatus status,
        LocalDateTime createdAt
) {}