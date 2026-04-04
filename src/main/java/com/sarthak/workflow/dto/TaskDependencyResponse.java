package com.sarthak.workflow.dto;

public record TaskDependencyResponse(
        Long id,
        Long taskId,
        Long dependsOnTaskId
) {}
