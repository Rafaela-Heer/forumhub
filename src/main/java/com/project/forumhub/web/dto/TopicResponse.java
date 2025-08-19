package com.project.forumhub.web.dto;

import com.project.forumhub.domain.Topic;
import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String status,
        String course,
        Long authorId,
        LocalDateTime createdAt
) {
    public static TopicResponse from(Topic t) {
        return new TopicResponse(
                t.getId(),
                t.getTitle(),
                t.getMessage(),
                t.getStatus() == null ? null : t.getStatus().toString(),
                t.getCourse(),
                t.getAuthorId(),
                t.getCreatedAt()
        );
    }
}
