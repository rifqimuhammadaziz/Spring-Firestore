package rifqimuhammadaziz.springfirebase.controller;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.dto.PostResponse;
import rifqimuhammadaziz.springfirebase.entity.Post;
import rifqimuhammadaziz.springfirebase.service.PostService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PostResponse> findAllPosts() throws ExecutionException, InterruptedException {
        return postService.findAllPosts();
    }

    @PostMapping("/create")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/{postId}")
    public Post findByPostId(@PathVariable String postId) throws ExecutionException, InterruptedException {
        return postService.findByPostId(postId);
    }

    @GetMapping("/category/{postCategory}")
    public List<Post> findAllPostsByCategory(@PathVariable String postCategory) throws ExecutionException, InterruptedException {
        return postService.findAllPostsByCategory(postCategory);
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
