package com.project.forumhub.web.dto;

import jakarta.validation.constraints.*;

public record TopicCreateRequest(
        @NotBlank @Size(max = 160) String title,
        @NotBlank String message,
        @NotBlank @Size(max = 120) String course,
        @NotNull Long authorId
) { }
