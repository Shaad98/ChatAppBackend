package com.example.chatappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatappbackend.model.Message;
import com.example.chatappbackend.model.Room;
import com.example.chatappbackend.service.RoomService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId)
    {
        if(roomService.getByRoomId(roomId)!=null)
        {
            // room is already there
            return ResponseEntity.badRequest().body("Room already exist!");
        }
        // create new room
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }

    // get room : join

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getMethodName(@PathVariable("roomId") String roomId) {
       Room room = roomService.getByRoomId(roomId);
       if(room==null)
       {
        return ResponseEntity.badRequest().body("Room not found!");
       }
       return ResponseEntity.ok(room);
    }
    
    // get room messages

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
                                @PathVariable String roomId,
                                @RequestParam(value = "page" , defaultValue = "0",required = false) int page,
                                @RequestParam(value = "size",defaultValue = "20",required = false) int size) {
        Room room = roomService.getByRoomId(roomId);
        if(room==null)
        {
            return ResponseEntity.badRequest().build();
        }
        // get messages
        // List<Message> messages = room.getMessages();
        // return ResponseEntity.ok(messages);

        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size()-(page+1)*size);
        int end = Math.min(messages.size(),start+size);

        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessages);
    }
    
}
