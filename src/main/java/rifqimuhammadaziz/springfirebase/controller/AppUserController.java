package rifqimuhammadaziz.springfirebase.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import rifqimuhammadaziz.springfirebase.dto.AppUserRequest;
import rifqimuhammadaziz.springfirebase.dto.AppUserResponse;
import rifqimuhammadaziz.springfirebase.dto.ResponseData;
import rifqimuhammadaziz.springfirebase.entity.AppUser;
import rifqimuhammadaziz.springfirebase.service.AppUserService;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registration")
    public ResponseEntity<ResponseData<?>> registration(@Valid @RequestBody AppUserRequest userRequest, Errors errors) {
        ResponseData<AppUserResponse> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        try {
            AppUser user = modelMapper.map(userRequest, AppUser.class);
            userService.registration(user);
            responseData.setStatus(true);
            responseData.getMessages().add("User successfully saved");
            responseData.setData(modelMapper.map(user, AppUserResponse.class));
            return ResponseEntity.ok(responseData);
        } catch (Exception exception) {
            responseData.getMessages().add(exception.getMessage());
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping
    public List<AppUserResponse> findAllUsers() throws ExecutionException, InterruptedException {
        return userService.findAllUsers();
    }

    @GetMapping("/{identityNumber}")
    public AppUserResponse findByIdentityNumber(@PathVariable String identityNumber) throws ExecutionException, InterruptedException {
        return userService.findByIdentityNumber(identityNumber);
    }
}
