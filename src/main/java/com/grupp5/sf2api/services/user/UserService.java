package com.grupp5.sf2api.services.user;

import com.grupp5.sf2api.exceptions.user.EmailIsEmptyException;
import com.grupp5.sf2api.exceptions.user.PasswordIsEmptyException;
import com.grupp5.sf2api.exceptions.user.UserAlreadyExistsException;
import com.grupp5.sf2api.exceptions.user.UserDoesntExistException;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.repositories.user.UserRepository;
import com.grupp5.sf2api.request.user.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User registerNewUser(User user) {
        Optional<User> existingUser = userRepository.findByUserId(user.getUserId());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists in database!");
        }

        if (user.getEmail().isEmpty()) {
            throw new EmailIsEmptyException("Email cannot be empty!");
        }

        if (user.getPasswordHash().isEmpty()) {
            throw new PasswordIsEmptyException("Password cannot be empty!");
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID userid, UpdateUserRequest request) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new UserDoesntExistException("User not found in database!"));

        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email());
        }

        if (request.password() != null && !request.password().isBlank()) {
            user.setPasswordHash(request.password());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteUser(UUID userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new UserDoesntExistException("User not found in database!"));

        userRepository.delete(user);

        return user;
    }


}
