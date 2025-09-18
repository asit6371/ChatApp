package com.asit.chatapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String senderUsername;
    private String receiverUsername;
    private String content;
    private LocalDateTime timestamp;
}
