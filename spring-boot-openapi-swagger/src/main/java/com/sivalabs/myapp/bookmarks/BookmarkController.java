package com.sivalabs.myapp.bookmarks;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtBearerAuth")
@Tag(name = "Bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkService.getAllBookmarks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.getBookmarkById(id);
        return ResponseEntity.ok(bookmark);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Operation(description = "Create a new bookmark", security = @SecurityRequirement(name = "jwtBearerAuth"))
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Successfully created new bookmark", responseCode = "201"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    public Bookmark createBookmark(@RequestBody @Valid Bookmark bookmark) {
        return bookmarkService.createBookmark(bookmark);
    }

}
