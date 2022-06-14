package rifqimuhammadaziz.springfirebase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.entity.User;
import rifqimuhammadaziz.springfirebase.service.UserService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public User findByDocumentId(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return userService.findByDocumentId(documentId);
    }

    @PostMapping("/create")
    public String create(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.create(user);
    }

    @PutMapping("/update")
    public String updateByDocumentId(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateByDocumentId(user);
    }

    @DeleteMapping("/delete")
    public String deleteByDocumentId(@RequestParam String documentId) {
        return userService.deleteByDocumentId(documentId);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test Get Endpoint is Works!");
    }
}
