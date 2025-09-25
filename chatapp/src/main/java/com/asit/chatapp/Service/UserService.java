package com.asit.chatapp.Service;

import com.asit.chatapp.Dto.UserLoginDto;
import com.asit.chatapp.Dto.UserRegDto;
import com.asit.chatapp.Dto.UserResponseDto;
import com.asit.chatapp.Entity.User;

import java.util.List;

public interface UserService {
    User registerNewUser(UserRegDto dto);

    User loginUser(UserLoginDto dto);

    User logoutUser(String username);

    List<UserResponseDto> getAllUsers();

    User getUserByUsername(String username);
}
