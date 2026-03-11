package dev.sivalabs.demo.api;

import dev.sivalabs.demo.domain.Comment;
import dev.sivalabs.demo.domain.PostInfo;

import java.time.Instant;

public record CommentResponse(
        Long id,
        String content,
        Instant createdAt) {

    public static CommentResponse from(PostInfo.CommentInfo comment) {
        return new CommentResponse(comment.getId(), comment.getContent(), comment.getCreatedAt());
    }
}
