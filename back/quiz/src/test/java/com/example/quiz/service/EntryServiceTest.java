package com.example.quiz.service;

import com.example.quiz.domain.entry.Entry;
import com.example.quiz.domain.room.Room;
import com.example.quiz.domain.user.User;
import com.example.quiz.dto.Entry.request.EntryCreateRequest;
import com.example.quiz.repository.entry.EntryRepository;
import com.example.quiz.repository.room.RoomRepository;
import com.example.quiz.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@NoArgsConstructor
public class EntryServiceTest {
    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("방인원 생성이 정상 작동한다")
    void createEntryTest(){
        //given
        Room saveTestRoom=roomRepository.save(Room.builder().
                roomName("test")
                .hostNo(1)
                .capacity(10)
                .regTime(LocalDateTime.now())
                .build());
        User saveTestUser=userRepository.save(User.builder()
                .userId("saveTestA")
                .userPw("saveTestA")
                .regTime(LocalDateTime.now())
                .build());

        EntryCreateRequest request=EntryCreateRequest.builder().roomNo(saveTestRoom.getRoomNo()).userNo(saveTestUser.getUserNo()).build();

        //when
        entryService.createEntry(request);

        //then
        List<Entry> results=entryRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUser().getUserNo()).isEqualTo(saveTestUser.getUserNo());
        assertThat(results.get(0).getRoom().getRoomNo()).isEqualTo(saveTestRoom.getRoomNo());
    }
}
