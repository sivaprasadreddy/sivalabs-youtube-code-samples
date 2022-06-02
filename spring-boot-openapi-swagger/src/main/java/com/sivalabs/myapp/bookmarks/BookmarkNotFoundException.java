package com.sivalabs.myapp.bookmarks;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookmarkNotFoundException extends RuntimeException {

    public BookmarkNotFoundException(Long customerId) {
        super("Bookmark with id="+customerId+" not found");
    }
}
