package com.example.chatappbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.chatappbackend.model.Message;
import com.example.chatappbackend.model.Room;
import com.example.chatappbackend.payload.MessageRequest;
import com.example.chatappbackend.service.RoomService;

@Controller
public class ChatController {

    @Autowired
    private RoomService roomService;


    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest messageRequest
    ){
        Room room = roomService.getByRoomId(messageRequest.getRoomId());

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setSender(messageRequest.getSender());

        if(room!=null)
        {
            room.getMessages().add(message);
            roomService.save(room);
        }
        else{
            throw new RuntimeException("Room not found!");
        }
        return message;
    }

}
