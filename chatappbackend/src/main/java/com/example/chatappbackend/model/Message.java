package com.example.chatappbackend.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
// @AllArgsConstructor
@ToString


@Document(collection = "messages")
public class Message {

    private String sender;
    private String content;
    private LocalDateTime timeStamp;

    public Message(String sender,String content){
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }

}
