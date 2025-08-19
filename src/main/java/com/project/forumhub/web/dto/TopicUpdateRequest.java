package com.project.forumhub.web.dto;

import com.project.forumhub.domain.TopicStatus;
import jakarta.validation.constraints.NotBlank;

public record TopicUpdateRequest(
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String course,
        TopicStatus status
) {}
