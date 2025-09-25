package com.asit.chatapp.Service;

import com.asit.chatapp.Dto.UserLoginDto;
import com.asit.chatapp.Dto.UserRegDto;
import com.asit.chatapp.Dto.UserResponseDto;
import com.asit.chatapp.Entity.User;
import com.asit.chatapp.Repository.UserRepository;
import com.asit.chatapp.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(UserRegDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw  new RuntimeException("User already taken");
        }
        User user = new User();
        user.setUsername(dto.getUsername());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStatus(Status.OFFLINE);
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User loginUser(UserLoginDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        user.setStatus(Status.ONLINE);
        return userRepository.save(user);
    }

    public User logoutUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getStatus() == Status.ONLINE) {
            user.setStatus(Status.OFFLINE);
        }

        return userRepository.save(user);
    }


    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(
                        user.getUsername(),
                        user.getStatus(),
                        user.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
