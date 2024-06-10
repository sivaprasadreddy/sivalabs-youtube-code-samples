package com.sivalabs.demo;

import java.time.Instant;

public record BookmarkCreatedEvent(
        String eventId,
        String title,
        String url,
        Instant createdAt) {
}