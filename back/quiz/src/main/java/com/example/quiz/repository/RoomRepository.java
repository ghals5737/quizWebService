package com.example.quiz.repository;

import com.example.quiz.dto.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
    Room findRoomByRoomNo(Long roomNo);
}
