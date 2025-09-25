package com.asit.chatapp.Controller;

import com.asit.chatapp.Dto.UserLoginDto;
import com.asit.chatapp.Dto.UserResponseDto;
import com.asit.chatapp.Service.UserServiceImpl;
import com.asit.chatapp.Utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLoginDto dto) {
        userServiceImpl.loginUser(dto);
        String token = jwtUtil.generateToken(dto.getUsername());

        return ResponseEntity.ok(Map.of(
                "username", dto.getUsername(),
                "token", token
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        String username = jwtUtil.extractUsername(token);

        userServiceImpl.logoutUser(username);

        return ResponseEntity.ok("user logged out successfully");
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }
}
