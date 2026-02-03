package com.grupp5.sf2api.services.user;

import com.grupp5.sf2api.dtos.user.DeletedUserDto;
import com.grupp5.sf2api.dtos.user.GetUserDto;
import com.grupp5.sf2api.dtos.user.UserAndTicketsDto;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.request.user.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User registerNewUser(User user);
    String loginUser(String email, String password);
    User updateUser(UUID userId, UpdateUserRequest request);
    List<GetUserDto> getAllUsers();
    DeletedUserDto deleteUser(UUID userId);
    List<UserAndTicketsDto> getAllTickets(UUID userId);
    User getSpecificUser(String email);
}
