package com.asit.chatapp.Controller;

import com.asit.chatapp.Dto.ChatMessageDto;
import com.asit.chatapp.Entity.Message;
import com.asit.chatapp.Entity.User;
import com.asit.chatapp.Repository.UserRepository;
import com.asit.chatapp.Service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessageDto) {
        User sender = userRepository.findByUsername(chatMessageDto.getSenderUsername())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findByUsername(chatMessageDto.getReceiverUsername())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));


        Message savedMessage = messageServiceImpl.saveMessage(sender, receiver, chatMessageDto.getContent());

        // send to receiver
        messagingTemplate.convertAndSendToUser(
                receiver.getUsername(),
                "/queue/messages",         // ensure this matches frontend subscribe
                savedMessage
        );
    }


}


