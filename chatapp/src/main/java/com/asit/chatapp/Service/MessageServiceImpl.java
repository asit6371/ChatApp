package com.asit.chatapp.Service;

import com.asit.chatapp.Entity.Message;
import com.asit.chatapp.Entity.User;
import com.asit.chatapp.Repository.MessageRepository;
import com.asit.chatapp.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message saveMessage(User sender, User receiver, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);       // ← add this line
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(Status.SENT);

        return messageRepository.save(message);
    }


    @Override
    public List<Message> getChatHistory(User sender, User receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }
}
