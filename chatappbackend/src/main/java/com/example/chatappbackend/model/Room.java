package com.example.chatappbackend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


@Document(collection = "rooms")
public class Room {

    @Id
    private String id;  // MongoDB unique identifier

    private String roomId;

    private List<Message> messages = new ArrayList<>();
}
