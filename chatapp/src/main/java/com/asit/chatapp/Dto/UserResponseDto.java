package com.asit.chatapp.Dto;

import com.asit.chatapp.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class UserResponseDto {
    private String username;
    private Status status;
    private LocalDateTime timeAt;


    public UserResponseDto(String username, Status status, LocalDateTime timeAt) {
        this.username = username;
        this.status = status;
        this.timeAt = timeAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(LocalDateTime timeAt) {
        this.timeAt = timeAt;
    }
}
