package com.example.chatappbackend.service;

import com.example.chatappbackend.model.Room;

public interface RoomService {

    Room getByRoomId(String roomId);
    Room save(Room room);
}
