package com.grupp5.sf2api.services.user;

import com.grupp5.sf2api.dtos.user.DeletedUserDto;
import com.grupp5.sf2api.dtos.user.GetUserDto;
import com.grupp5.sf2api.dtos.user.UserAndTicketsDto;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.request.user.LoginUserRequest;
import com.grupp5.sf2api.request.user.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User registerNewUser(User user);
    String loginUser(String email, String password);
    User updateUser(UUID userid, UpdateUserRequest request);
    List<GetUserDto> getAllUsers();
    DeletedUserDto deleteUser(UUID userid);
    List<UserAndTicketsDto> getAllTickets(UUID userid);
    User getSpecificUser(String email);
}
