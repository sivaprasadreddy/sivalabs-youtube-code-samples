package com.sivalabs.myapp.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
