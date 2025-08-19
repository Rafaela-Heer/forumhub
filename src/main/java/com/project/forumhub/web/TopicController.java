package com.project.forumhub.web;

import com.project.forumhub.domain.Topic;
import com.project.forumhub.repository.TopicRepository;
import com.project.forumhub.web.dto.TopicCreateRequest;
import com.project.forumhub.web.dto.TopicResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository repository;

    public TopicController(TopicRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid TopicCreateRequest req) {
        if (repository.existsByTitleAndMessage(req.title(), req.message())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tópico duplicado (título + mensagem).");
        }

        Topic t = new Topic();
        t.setTitle(req.title());
        t.setMessage(req.message());
        t.setCourse(req.course());
        t.setAuthorId(req.authorId());


        Topic saved = repository.save(t);
        return ResponseEntity
                .created(URI.create("/topics/" + saved.getId()))
                .body(TopicResponse.from(saved));
    }

    @GetMapping
    public Page<TopicResponse> list(
            @RequestParam(name = "course", required = false) String course,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Topic> page = (course == null || course.isBlank())
                ? repository.findAll(pageable)
                : repository.findByCourseContainingIgnoreCase(course, pageable);

        return page.map(TopicResponse::from);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(topic -> ResponseEntity.ok(TopicResponse.from(topic)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
