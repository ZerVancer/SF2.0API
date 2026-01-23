package com.grupp5.sf2api.services.user;

import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.request.user.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User registerNewUser(User user);
    User updateUser(UUID userid, UpdateUserRequest request);
    List<User> getAllUsers();
    User deleteUser(UUID userid);
}
