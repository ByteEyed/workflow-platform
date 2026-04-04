package com.sarthak.workflow.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        String role,
        String createdAt
) {}
