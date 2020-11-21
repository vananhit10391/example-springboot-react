package vananh.example.multitenant.controller;

import vananh.example.common.exception.ResourceNotFoundException;
import vananh.example.common.payload.response.ApiResponse;
import vananh.example.multitenant.service.UserService;
import vananh.example.multitenant.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userService.getById(id);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @PutMapping(value = "/")
    public ResponseEntity<User> update(@RequestBody User user) {
        Optional<User> userOptional = userService.getById(user.getId());
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User", "id", user.getId());
        }
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userService.getById(id);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Detele success"));
    }
}
