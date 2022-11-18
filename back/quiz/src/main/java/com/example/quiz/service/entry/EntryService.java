package com.example.quiz.service.entry;

import com.example.quiz.domain.entry.Entry;
import com.example.quiz.dto.Entry.request.EntryCreateRequest;
import com.example.quiz.global.error.code.ErrorCode;
import com.example.quiz.global.error.exception.CustomException;
import com.example.quiz.repository.entry.EntryQuerydslRepository;
import com.example.quiz.repository.entry.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;
    private final EntryQuerydslRepository entryQuerydslRepository;

    public void createEntry(EntryCreateRequest request){
        entryRepository.save(request.toEntry());
    }

    public List<Entry> findEntryByRoomNo(long roomNo) {
        List<Entry> results=entryQuerydslRepository.findEntryByRoomNo(roomNo);
        if(results==null||results.isEmpty()){
            throw new CustomException(ErrorCode.ROOM_NOT_FOUND);
        }
        return results;
    }

    public long countEntryByRoom(long roomNo) {
        return entryQuerydslRepository.countEntryByRoom(roomNo);
    }
}
