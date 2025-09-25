package com.asit.chatapp.Service;

import com.asit.chatapp.Entity.Message;
import com.asit.chatapp.Entity.User;

import java.util.List;

public interface MessageService {
    Message saveMessage(User sender, User receiver, String content);
    List<Message> getChatHistory(User sender, User receiver);
}
