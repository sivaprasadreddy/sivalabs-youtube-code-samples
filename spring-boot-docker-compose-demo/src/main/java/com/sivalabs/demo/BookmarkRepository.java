package com.sivalabs.demo;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class BookmarkRepository {
    private final JdbcClient jdbcClient;

    public BookmarkRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Bookmark> findAll() {
        String sql = "select id, title, url, created_at from bookmarks";
        return jdbcClient.sql(sql).query(Bookmark.class).list();
    }

    public Optional<Bookmark> findById(Long id) {
        String sql = "select id, title, url, created_at from bookmarks where id = :id";
        return jdbcClient.sql(sql).param("id", id).query(Bookmark.class).optional();
    }

    @Transactional
    public Long save(Bookmark bookmark) {
        String sql = """
                    insert into bookmarks(title, url, created_at)
                    values(:title,:url,:createdAt)
                    """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("title", bookmark.title())
                .param("url", bookmark.url())
                .param("createdAt", Timestamp.from(bookmark.createdAt()))
                .update(keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }
}