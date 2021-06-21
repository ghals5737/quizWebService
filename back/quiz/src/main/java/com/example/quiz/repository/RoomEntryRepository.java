package com.example.quiz.repository;

import com.example.quiz.dto.RoomEntry;
import com.example.quiz.dto.RoomEntryId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomEntryRepository extends CrudRepository<RoomEntry, RoomEntryId> {
}
