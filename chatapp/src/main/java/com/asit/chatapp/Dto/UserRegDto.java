package com.asit.chatapp.Dto;

import com.asit.chatapp.Status;
import java.time.LocalDateTime;


public class UserRegDto {

    private String username;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Status status;

    public UserRegDto() {
    }

    public UserRegDto(String username, String password, LocalDateTime createdAt, Status status) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
