package com.sarthak.workflow.auth;

public record LoginRequest(
    String username,
    String password
) {}