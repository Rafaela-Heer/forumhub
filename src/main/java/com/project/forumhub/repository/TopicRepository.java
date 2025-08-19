package com.project.forumhub.repository;

import com.project.forumhub.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findByCourseContainingIgnoreCase(String course, Pageable pageable);
}
