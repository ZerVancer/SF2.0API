package com.grupp5.sf2api.services.user;

import com.grupp5.sf2api.dtos.user.DeletedUserDto;
import com.grupp5.sf2api.dtos.user.GetUserDto;
import com.grupp5.sf2api.dtos.user.UserAndTicketsDto;
import com.grupp5.sf2api.exceptions.user.EmailIsEmptyException;
import com.grupp5.sf2api.exceptions.user.PasswordIsEmptyException;
import com.grupp5.sf2api.exceptions.user.UserAlreadyExistsException;
import com.grupp5.sf2api.exceptions.user.UserDoesntExistException;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.repositories.user.UserRepository;
import com.grupp5.sf2api.request.user.UpdateUserRequest;
import com.grupp5.sf2api.utils.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final JWTService jWTService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(User user) {

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new EmailIsEmptyException();
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        if (user.getPasswordHash() == null || user.getPasswordHash().isBlank()) {
            throw new PasswordIsEmptyException();
        }

        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        return userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserDoesntExistException::new);

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return null;
        }

        return jWTService.generateToken(user.getUserId());
    }

    @Override
    public User updateUser(UUID userid, UpdateUserRequest request) {
        User user = userRepository.findById(userid)
                .orElseThrow(UserDoesntExistException::new);

        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email());
        }

        if (request.password() != null && !request.password().isBlank()) {
            user.setPasswordHash(passwordEncoder.encode(request.password()));
        }

        return userRepository.save(user);
    }

    @Override
    public List<GetUserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(GetUserDto::from)
                .toList();
    }

    @Override
    public DeletedUserDto deleteUser(UUID userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(UserDoesntExistException::new);

        userRepository.delete(user);

        return DeletedUserDto.from(user);
    }

    @Override
    public List<UserAndTicketsDto> getAllTickets(UUID userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(UserDoesntExistException::new);

        return List.of(UserAndTicketsDto.from(user));
    }

    @Override
    public User getSpecificUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserDoesntExistException::new);
    }

}
