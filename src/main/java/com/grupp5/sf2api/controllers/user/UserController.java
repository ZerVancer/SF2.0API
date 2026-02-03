package com.grupp5.sf2api.controllers.user;

import com.grupp5.sf2api.dtos.user.*;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.request.user.LoginUserRequest;
import com.grupp5.sf2api.request.user.RegisterUserRequest;
import com.grupp5.sf2api.request.user.UpdateUserRequest;
import com.grupp5.sf2api.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> registerNewUser(@RequestBody RegisterUserRequest request) {
        User user = new User(
                request.email(),
                request.password()
        );

        User newUser = userService.registerNewUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RegisterUserDto.from(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest request) {
        try {
            String token = userService.loginUser(
                    request.email(),
                    request.password()
            );

            return ResponseEntity.ok(
                    Map.of("token", token));

        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("Error", exception.getMessage()));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<DeletedUserDto> deleteUser(@PathVariable UUID userId) {
        DeletedUserDto deletedUser = userService.deleteUser(userId);

        return ResponseEntity.ok(deletedUser);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UpdatedUserDto> updateUser(@PathVariable UUID userId,
                                                     @RequestBody UpdateUserRequest request) {
        User updatedUser = userService.updateUser(
                userId,
                request
        );

        return ResponseEntity.ok(UpdatedUserDto.from(updatedUser));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/tickets/{userId}")
    public ResponseEntity<List<UserAndTicketsDto>> getAllTicketsForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getAllTickets(userId));
    }

    @GetMapping("/specific/{email}")
    public ResponseEntity<GetUserDto> getSpecificUser(@PathVariable String email) {
        User user = userService.getSpecificUser(email);

        if (user == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(GetUserDto.from(user));
    }
}
