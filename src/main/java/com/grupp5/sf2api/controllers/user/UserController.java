package com.grupp5.sf2api.controllers.user;

import com.grupp5.sf2api.dtos.user.DeletedUserDto;
import com.grupp5.sf2api.dtos.user.RegisterUserDto;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.request.user.RegisterUserRequest;
import com.grupp5.sf2api.request.user.UpdateUserRequest;
import com.grupp5.sf2api.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> registerNewUser(@RequestBody RegisterUserRequest request) {

        User user = new User(request.email(), request.password());

        User newUser = userService.registerNewUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterUserDto.from(newUser));
    }

    @PutMapping("/update/{userid}")
    public ResponseEntity<User> updateUser(
            @PathVariable UUID userid,
            @RequestBody UpdateUserRequest request
            ) {

        User updatedUser = userService.updateUser(userid, request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<DeletedUserDto> deleteUser(
            @PathVariable UUID userid
    ) {
        User deletedUser = userService.deleteUser(userid);

        return ResponseEntity.ok(DeletedUserDto.from(deletedUser));
    }
}
