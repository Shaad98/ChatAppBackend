package com.example.chatappbackend.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatappbackend.model.Room;
import com.example.chatappbackend.repository.RoomRepository;
import com.example.chatappbackend.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room getByRoomId(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        return room;
    }

    @Override
    public Room save(Room room) {
        Room room2 = roomRepository.save(room);
        return room2;
    }

    
}
