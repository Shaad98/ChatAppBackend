package com.example.chatappbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.chatappbackend.model.Room;

public interface RoomRepository extends MongoRepository<Room,String>{

    // get room using room id
    Room findByRoomId(String roomId);
}
