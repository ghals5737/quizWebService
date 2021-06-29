package com.example.quiz.repository;

import com.example.quiz.dto.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomByRoomNo(Long roomNo);
}
