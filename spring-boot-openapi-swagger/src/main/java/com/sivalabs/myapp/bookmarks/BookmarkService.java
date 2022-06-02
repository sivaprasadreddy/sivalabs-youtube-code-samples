package com.sivalabs.myapp.bookmarks;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final Logger log = LoggerFactory.getLogger(BookmarkService.class);

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll();
    }

    public Bookmark createBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Transactional(readOnly = true)
    public Bookmark getBookmarkById(Long id) {
        log.debug("Get bookmark by id: {}", id);
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException(id));
    }
}
