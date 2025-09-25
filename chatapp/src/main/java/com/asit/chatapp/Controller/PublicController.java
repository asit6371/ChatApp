package com.asit.chatapp.Controller;



import com.asit.chatapp.Dto.UserRegDto;
import com.asit.chatapp.Entity.User;
import com.asit.chatapp.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/health_check")
    public String healthCheck() {
        return "OK";
    }



    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegDto dto) {
        User registerNewUser = userService.registerNewUser(dto);
        return ResponseEntity.ok(registerNewUser);
    }

}
