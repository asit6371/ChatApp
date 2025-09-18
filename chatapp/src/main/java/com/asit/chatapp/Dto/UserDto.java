package com.asit.chatapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status = "OFFLINE";
}
