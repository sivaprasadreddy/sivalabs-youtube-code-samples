package dev.sivalabs.demo.domain;

import java.time.Instant;
import java.util.List;

/**
 * Projection for {@link Post}
 */
public interface PostInfo {
    Long getId();

    String getTitle();

    String getContent();

    Instant getCreatedAt();

    List<CommentInfo> getComments();

    /**
     * Projection for {@link Comment}
     */
    interface CommentInfo {
        Long getId();

        String getContent();

        Instant getCreatedAt();
    }
}