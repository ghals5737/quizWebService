package com.example.quiz.repository;

import com.example.quiz.dto.Quiz;
import com.example.quiz.dto.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomByRoomNo(Long roomNo);
    Page<Room> findAll(Pageable pageable);
}
