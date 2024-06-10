package com.sivalabs.demo;

public record CreateBookmarkRequest(
        String title,
        String url) {
}