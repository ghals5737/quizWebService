package com.example.quiz.repository.entry;

import com.example.quiz.domain.entry.Entry;
import com.example.quiz.domain.entry.EntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, EntryId> {
}
