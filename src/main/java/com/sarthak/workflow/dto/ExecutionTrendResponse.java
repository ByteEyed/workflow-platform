package com.sarthak.workflow.dto;

public record ExecutionTrendResponse(
        String date,
        long success,
        long failed
) {}
