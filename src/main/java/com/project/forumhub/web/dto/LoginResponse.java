package com.project.forumhub.web.dto;

public record LoginResponse(
        String token, String tokenType,
        Long id, String name, String email, String role
) {}