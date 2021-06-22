package com.example.quiz.repository;

import com.example.quiz.dto.Room;
import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomEntryId;
import com.example.quiz.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoomEntryRepository extends CrudRepository<RoomEntry, RoomEntryId> {
    List<RoomEntry> findRoomEntryByRoomOrderByUserScore(Room room);
    List<RoomEntry> findRoomEntryByUserOrderByRoom(User user);
    @Transactional
    void deleteRoomEntriesByRoom(Room room);
}
