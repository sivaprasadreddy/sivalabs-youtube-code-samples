package dev.sivalabs.demo.api;

import dev.sivalabs.demo.domain.Post;
import dev.sivalabs.demo.domain.PostInfo;
import dev.sivalabs.demo.domain.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {
        List<PostInfo> postEntities = postService.findAllPosts();
        List<PostResponse> allPosts = postEntities
                .stream()
                .map(PostResponse::from)
                .toList();
        return ResponseEntity.ok(allPosts);
    }
}
