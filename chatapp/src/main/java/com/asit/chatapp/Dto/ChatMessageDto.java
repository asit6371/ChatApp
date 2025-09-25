package com.asit.chatapp.Dto;


public class ChatMessageDto {

    private String content;
    private String senderUsername;
    private String receiverUsername;


    public ChatMessageDto() {

    }

    public ChatMessageDto(String content, String senderUsername, String receiverUsername) {
        this.content = content;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
