package com.sivalabs.myapp.bookmarks;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Bookmarks")
public class TagController {

    @GetMapping("/api/tags")
    public void handle() {

    }
}
