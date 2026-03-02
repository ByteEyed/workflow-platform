package com.sarthak.workflow.auth;

import com.sarthak.workflow.domain.entity.User;

public record LoginResponse(
    String token,
    User user
) {}