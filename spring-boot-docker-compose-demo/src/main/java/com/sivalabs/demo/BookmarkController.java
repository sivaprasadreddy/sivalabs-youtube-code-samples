package com.sivalabs.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bookmarks")
class BookmarkController {
    private static final Logger log = LoggerFactory.getLogger(BookmarkController.class);

    private final BookmarkRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    BookmarkController(BookmarkRepository repository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    List<Bookmark> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Bookmark> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody CreateBookmarkRequest bookmark) {
        var b = new Bookmark(null, bookmark.title(), bookmark.url(), Instant.now());
        repository.save(b);
        publishEvent(b);
    }

    void publishEvent(Bookmark b) {
        Instant now = Instant.now();
        log.info("Sending a BookmarkCreatedEvent to kafka at {}", now);
        BookmarkCreatedEvent event = new BookmarkCreatedEvent(
                UUID.randomUUID().toString(), b.title(), b.url(), now);
        kafkaTemplate.send("demo-topic", event);
        log.info("Sent event {} to kafka topic: demo-topic", event);
    }
}