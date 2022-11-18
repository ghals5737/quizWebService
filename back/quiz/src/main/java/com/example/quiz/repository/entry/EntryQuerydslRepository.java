package com.example.quiz.repository.entry;

import static com.example.quiz.domain.entry.QEntry.*;

import com.example.quiz.domain.entry.Entry;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class EntryQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<Entry> findEntryByRoomNo(long roomNo){
        return queryFactory.select(entry)
                .from(entry)
                .where(
                        entry.room.roomNo.eq(roomNo)
                )
                .fetch();
    }

    public long countEntryByRoom(long roomNo){
        return queryFactory.select(entry.count())
                .from(entry)
                .where(
                        entry.room.roomNo.eq(roomNo)
                ).fetchFirst();
    }
}
