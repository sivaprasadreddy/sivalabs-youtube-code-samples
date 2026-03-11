package dev.sivalabs.demo.api;

import dev.sivalabs.demo.domain.Post;
import dev.sivalabs.demo.domain.PostInfo;

import java.time.Instant;
import java.util.List;

public record PostResponse(
        Long id,
        String title,
        String content,
        Instant createdAt,
        List<CommentResponse> comments) {

    public static PostResponse from(PostInfo post) {
        List<CommentResponse> commentResponses = post.getComments().stream().map(CommentResponse::from).toList();
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                commentResponses
        );
    }
}
