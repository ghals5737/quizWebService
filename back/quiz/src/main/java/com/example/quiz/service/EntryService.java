package com.example.quiz.service;

import com.example.quiz.dto.Entry.request.EntryCreateRequest;
import com.example.quiz.repository.entry.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;

    public void createEntry(EntryCreateRequest request){
        entryRepository.save(request.toEntry());
    }
}
