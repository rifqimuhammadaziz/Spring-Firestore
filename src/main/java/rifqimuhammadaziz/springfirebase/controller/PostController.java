package rifqimuhammadaziz.springfirebase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.entity.Post;
import rifqimuhammadaziz.springfirebase.service.PostService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/{postId}")
    public Post findByPostId(@PathVariable String postId) throws ExecutionException, InterruptedException {
        return postService.findByPostId(postId);
    }

    @PutMapping("/update")
    public String updateByPostId(@RequestBody Post post) throws ExecutionException, InterruptedException {
        return postService.updateByPostId(post);
    }

    @DeleteMapping("/delete/{postId}")
    public String deleteByPostId(@PathVariable String postId) throws ExecutionException, InterruptedException {
        return postService.deleteByPostId(postId);
    }
}
