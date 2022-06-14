package rifqimuhammadaziz.springfirebase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.entity.User;
import rifqimuhammadaziz.springfirebase.service.UserService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/document_id")
    public String findByDocumentId(@RequestParam String documentId) {
        return userService.findByDocumentId(documentId);
    }

    @PostMapping("/create")
    public String create(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.create(user);
    }

    @PutMapping("/update")
    public String updateByDocumentId(@RequestBody User user) {
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
